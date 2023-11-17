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
                        error = true;
                        System.err.println("Fatal error: " + e.getMessage());
                    } finally {
                        error = symTable.error();
                        if (symTable.error()) System.out.println();
                        System.out.print(symTable);
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
