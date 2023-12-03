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
    private static Program program = null;
    private static Stage stage = Stage.NONE;

    private enum Stage {
        NONE,
        PARSE,
        CHECK,
        CODEGEN
    }

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
        if (options.isEmpty()) stage(Stage.CODEGEN);
        else options.forEach(MiniJava::compilerOption);
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
                    stage(Stage.CHECK);
                    if (symTable.error()) System.out.println();
                    System.out.print(symTable);
                    break;
                default:
                    System.err.println("Passed unrecognizable flag.");
                    error = true;
            }
            System.out.println();
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " + e);
            // print out a stack dump
            e.printStackTrace();
            error = true;
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
        System.out.println();
    }

    private static void visitAST(Visitor v) {
        if (program == null) stage(Stage.PARSE);
        program.accept(v);
    }

    private static void stage(Stage required) {
        try {
            switch (stage) {
                default:
                case NONE:
                case PARSE:
                    if (stage.compareTo(required) >= 0 || error) return;
                    stage = Stage.PARSE;
                    if (program == null) {
                        program = (Program) new parser(getScanner(), sf).parse().value;
                    }
                case CHECK:
                    if (stage.compareTo(required) >= 0 || error) return;
                    stage = Stage.CHECK;
                    visitAST(new PopulateTableVisitor(symTable));
                    visitAST(new ExpressionTypeVisitor(symTable));
                    error |= symTable.error();
                case CODEGEN:
                    if (stage.compareTo(required) >= 0 || error) return;
                    stage = Stage.CODEGEN;
                    visitAST(new CodeGenerationVisitor(symTable));  
            }
        } catch (Exception e) {
            error = true;
            switch (stage) {
                case CHECK:
                    System.err.println("Fatal error in performing semantic checks: " + e.getMessage());
                    break;
                case CODEGEN:
                    System.err.println("Fatal error in code generation: " + e.getMessage());
                    break;
                case PARSE:
                    System.err.println("Fatal error in parsing program: " + e.getMessage());
                    break;
                case NONE:
                default:
            }
        }
    }
}
