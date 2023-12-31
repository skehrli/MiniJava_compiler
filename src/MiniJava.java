import AST.*;
import AST.Visitor.*;
import Semantics.*;
import Scanner.*;
import Parser.*;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

public class MiniJava {
    private static final ComplexSymbolFactory sf = new ComplexSymbolFactory();
    private static String filename;
    private static boolean error = false;
    private static final Set<Character> flags = Set.of('S', 'A', 'P', 'T');
    private static final SymbolTable symTable = new SymbolTable();
    static Program program = null;

    public static void main(String[] args) {
        filename = args[args.length - 1];

        Set<Character> options = new LinkedHashSet<>();
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].charAt(0) != '-') {
                System.err.println("Could not parse compiler flags.");
                System.exit(1);
            }
            for (int j = 1; j < args[i].length(); j++) {
                char ch = args[i].charAt(j);
                if (!flags.contains(ch)) {
                    System.err.println("Could not recognize compiler flag '" + ch + "'");
                    System.exit(1);
                }
                options.add(ch);
            }
        }
        options.forEach(MiniJava::compilerOption);
        System.exit(error ? 1 : 0);
    }

    private static void printTable() {
        for (String s : symTable.classes.keySet()) {
            ClassType cl = symTable.get(s);
            if (cl == MainClassType.get()) {
                System.out.format("main class %s\n", cl);
                continue;
            }
            if (cl == Bottom.get()) {
                System.out.format("class %s :: %s\n", s, cl);
                continue;
            }
            DeclaredClass cls = (DeclaredClass) cl;
            System.out.format("class %s%s\n", s, cls.superclass() != Top.get() ? " <: " + cls.superclass : "");

            for (String decl : cls.instances.keySet()) {
                System.out.format("\tfield %s :: %s\n", decl, cls.getField(decl));
            }
            for (String decl : cls.methods.keySet()) {
                StringBuilder b = new StringBuilder("\tmethod ");
                b.append(decl).append(" :: ");
                if (cls.getMethod(decl) == Bottom.get())
                    b.append(Bottom.get());
                else {
                    b.append('(');
                    ((Method) cls.getMethod(decl)).parameters.forEach((key, value) -> b.append(key).append(": ").append(value).append(", "));
                    if (!((Method) cls.getMethod(decl)).parameters.isEmpty()) b.delete(b.length() - 2, b.length());
                    b.append(") -> ").append(cls.getMethod(decl).getReturn());
                }

                System.out.println(b);
                for (String var : ((Method) cls.methods.get(decl)).variables.keySet()) {
                    System.out.format("\t\tvariable %s :: %s\n", var,
                            ((Method) cls.methods.get(decl)).variables.get(var).toString());
                }
            }
        }
    }

    private static void compilerOption(char ch) {
        try {
            switch (ch) {
                case 'S':
                    printTokens();
                    break;
                case 'A':
                    visitAST(new ASTPrintVisitor());
                    break;
                case 'P':
                    visitAST(new PrettyPrintVisitor());
                    break;
                case 'T':
                    Top.symTable = symTable;
                    try {
                        visitAST(new PopulateTable(symTable));
                        visitAST(new ExpressionTypeVisitor(symTable));
                    } catch (RuntimeException e){
                        System.err.println("Fatal error: " + e.getMessage() + "\n");
                    } finally {
                        printTable();
                    }
                    break;
                default:
                    throw new RuntimeException("Passed unrecognizable flag.");
            }
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " + e);
            // print out a stack dump
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static scanner getScanner() {
        try {
            return new scanner(new BufferedReader(new FileReader(filename)), sf);
        } catch (FileNotFoundException e) {
            System.err.println("File '" + filename + "' not found.");
            System.exit(1);
            return null;
        }
    }

    private static void printTokens() throws IOException {
        scanner s = getScanner();
        Symbol t = s.next_token();
        while (t.sym != sym.EOF) {
            // print each token that we scan
            System.out.print(s.symbolToString(t) + " ");
            t = s.next_token();
            if (t.sym == sym.error) {
                error = true;
            }
        }
    }

    private static void visitAST(Visitor v) throws Exception {
        if (program == null) {
            parser p = new parser(getScanner(), sf);
            Symbol root = p.parse();
            program = (Program) root.value;
        }
        program.accept(v);
    }
}
