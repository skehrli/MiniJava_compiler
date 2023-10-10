import Scanner.*;
import Parser.sym;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java.io.*;

public class MiniJava {
    public static void main(String[] args) {
        try {
            // create a scanner on the input file
            ComplexSymbolFactory sf = new ComplexSymbolFactory();
            String filename = args[args.length - 1];
            FileReader fileReader = new FileReader(filename);
            Reader in = new BufferedReader(fileReader);
            scanner s = new scanner(in, sf);
            Symbol t = s.next_token();
            // args[0] is the compiler flag
            if (!args[0].equals("-S")) System.exit(0);
            while (t.sym != sym.EOF) {
                // print each token that we scan
                System.out.print(s.symbolToString(t) + " ");
                t = s.next_token();
            }
            System.exit(0);
        } catch (Exception e) {
            // yuck: some kind of error in the compiler implementation
            // that we're not expecting (a bug!)
            System.err.println("Unexpected internal compiler error: " + e);
            // print out a stack dump
            e.printStackTrace();
            System.exit(1);
        }
    }
}
