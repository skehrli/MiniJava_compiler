package AST.Visitor;

import AST.*;

// Sample print visitor from MiniJava web site with small modifications for UW CSE.
// HP 10/11

public class ASTPrintVisitor implements Visitor {
    private int indent = 0;

    private void printLine(ASTNode n) {
        System.out.print(" (line " + n.line_number + ")");
    }

    private void indent() {
        for (int i = 0; i < indent; i++)
            System.out.print("\t");
    }

    // MainClass m;
    // ClassDeclList cl;
    public void visit(Program n) {
        indent();
        System.out.println("Program");
        indent++;
        n.m.accept(this);
        for (int i = 0; i < n.cl.size(); i++) {
            System.out.println();
            n.cl.get(i).accept(this);
        }
        indent--;
    }

    // Identifier i1,i2;
    // Statement s;
    public void visit(MainClass n) {
        indent();
        System.out.print("MainClass ");
        n.i1.accept(this);
        printLine(n);
        System.out.println();

        indent++;
        n.s.accept(this);
        indent--;
        System.out.println();
    }

    // Identifier i;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclSimple n) {
        indent();
        System.out.print("Class ");
        n.i.accept(this);
        printLine(n);

        indent++;
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.get(i).accept(this);
            if (i + 1 < n.vl.size()) {
                System.out.println();
            }
        }

        for (int i = 0; i < n.ml.size(); i++) {
            System.out.println();
            n.ml.get(i).accept(this);
        }
        indent--;
    }

    // Identifier i;
    // Identifier j;
    // VarDeclList vl;
    // MethodDeclList ml;
    public void visit(ClassDeclExtends n) {
        indent();
        System.out.print("Class ");
        n.i.accept(this);
        System.out.print(" extends ");
        n.j.accept(this);
        printLine(n);

        indent++;
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.get(i).accept(this);
            if (i + 1 < n.vl.size()) {
                System.out.println();
            }
        }

        for (int i = 0; i < n.ml.size(); i++) {
            System.out.println();
            n.ml.get(i).accept(this);
        }
        indent--;
    }

    // Type t;
    // Identifier i;
    public void visit(VarDecl n) {
        n.i.accept(this);
        System.out.print(": ");
        n.t.accept(this);
    }

    // Type t;
    // Identifier i;
    // FormalList fl;
    // VarDeclList vl;
    // StatementList sl;
    // Exp e;
    public void visit(MethodDecl n) {

        // Declaration line
        // f: (x: int, y: int, z: int) -> int
        indent();
        n.i.accept(this);
        System.out.print(": (");
        for (int i = 0; i < n.fl.size(); i++) {
            n.fl.get(i).accept(this);
            if (i + 1 < n.fl.size()) {
                System.out.print(", ");
            }
        }
        System.out.print(") -> ");
        n.t.accept(this);
        printLine(n);
        System.out.println();

        indent++;
        for (int i = 0; i < n.vl.size(); i++) {
            // Variable declaration line:
            // x: int
            indent();
            n.vl.get(i).accept(this);
            System.out.println();
        }
        for (int i = 0; i < n.sl.size(); i++) {
            // Statement line
            indent();
            n.sl.get(i).accept(this);
            if (i < n.sl.size()) {
                System.out.println();
            }
        }

        // Return statement line
        // return exp
        indent();
        System.out.print("return ");
        n.e.accept(this);
        System.out.println();
        indent--;
    }

    // Type t;
    // Identifier i;
    public void visit(Formal n) {
        n.i.accept(this);
        System.out.print(": ");
        n.t.accept(this);
    }

    public void visit(IntArrayType n) {
        System.out.print("int []");
    }

    public void visit(BooleanType n) {
        System.out.print("boolean");
    }

    public void visit(IntegerType n) {
        System.out.print("int");
    }

    // String s;
    public void visit(IdentifierType n) {
        System.out.print(n.s);
    }

    // StatementList sl;
    public void visit(Block n) {
        indent();
        System.out.print("Block ");
        indent++;
        printLine(n);
        System.out.println();

        for (int i = 0; i < n.sl.size(); i++) {
            indent();
            n.sl.get(i).accept(this);
            System.out.println();
        }
        indent--;
    }

    // Exp e;
    // Statement s1,s2;
    public void visit(If n) {
        indent();
        System.out.print("If ");
        n.e.accept(this);
        printLine(n);
        System.out.println();

        indent++;
        n.s1.accept(this);
        System.out.println();
        indent--;

        indent();
        System.out.print("Else ");
        indent++;
        n.s2.accept(this);
        indent--;
    }

    // Exp e;
    // Statement s;
    public void visit(While n) {
        indent();
        System.out.print("While ");
        n.e.accept(this);
        printLine(n);
        System.out.println();

        indent++;
        n.s.accept(this);
        indent--;
    }

    // Exp e;
    public void visit(Print n) {
        indent();
        System.out.print("Print");
        printLine(n);
        System.out.println();

        indent++;
        indent();
        n.e.accept(this);
        indent--;
    }

    // Identifier i;
    // Exp e;
    public void visit(Assign n) {
        indent();
        System.out.print("Assign");
        printLine(n);
        System.out.println();

        indent++;
        indent();
        n.i.accept(this);
        System.out.print(" := ");
        n.e.accept(this);
        indent--;
    }

    // Identifier i;
    // Exp e1,e2;
    public void visit(ArrayAssign n) {
        indent();
        System.out.print("ArrayAssign");
        printLine(n);
        System.out.println();

        indent++;
        indent();
        n.i.accept(this);
        System.out.print("[");
        n.e1.accept(this);
        System.out.print("] := ");
        n.e2.accept(this);
        indent--;
    }

    // Exp e1,e2;
    public void visit(And n) {
        System.out.print("(");
        n.e1.accept(this);
        System.out.print(" && ");
        n.e2.accept(this);
        System.out.print(")");
    }

    // Exp e1,e2;
    public void visit(LessThan n) {
        System.out.print("(");
        n.e1.accept(this);
        System.out.print(" < ");
        n.e2.accept(this);
        System.out.print(")");
    }

    // Exp e1,e2;
    public void visit(Plus n) {
        System.out.print("(");
        n.e1.accept(this);
        System.out.print(" + ");
        n.e2.accept(this);
        System.out.print(")");
    }

    // Exp e1,e2;
    public void visit(Minus n) {
        System.out.print("(");
        n.e1.accept(this);
        System.out.print(" - ");
        n.e2.accept(this);
        System.out.print(")");
    }

    // Exp e1,e2;
    public void visit(Times n) {
        System.out.print("(");
        n.e1.accept(this);
        System.out.print(" * ");
        n.e2.accept(this);
        System.out.print(")");
    }

    // Exp e1,e2;
    public void visit(ArrayLookup n) {
        System.out.print("(");
        n.e1.accept(this);
        System.out.print("[");
        n.e2.accept(this);
        System.out.print("]");
        System.out.print(")");
    }

    // Exp e;
    public void visit(ArrayLength n) {
        System.out.print("(");
        n.e.accept(this);
        System.out.print(".length");
        System.out.print(")");
    }

    // Exp e;
    // Identifier i;
    // ExpList el;
    public void visit(Call n) {
        n.e.accept(this);
        System.out.print(".");
        n.i.accept(this);
        System.out.print("(");
        for (int i = 0; i < n.el.size(); i++) {
            n.el.get(i).accept(this);
            if (i + 1 < n.el.size()) {
                System.out.print(", ");
            }
        }
        System.out.print(")");
    }

    // int i;
    public void visit(IntegerLiteral n) {
        System.out.print(n.i);
    }

    public void visit(True n) {
        System.out.print("true");
    }

    public void visit(False n) {
        System.out.print("false");
    }

    // String s;
    public void visit(IdentifierExp n) {
        System.out.print(n.s);
    }

    public void visit(This n) {
        System.out.print("this");
    }

    // Exp e;
    public void visit(NewArray n) {
        indent();
        System.out.print("new int [");
        n.e.accept(this);
        System.out.print("]");
    }

    // Identifier i;
    public void visit(NewObject n) {
        indent();
        System.out.print("new ");
        n.i.accept(this);
        System.out.print("()");
    }

    // Exp e;
    public void visit(Not n) {
        indent();
        System.out.print("! ");
        n.e.accept(this);
    }

    // String s;
    public void visit(Identifier n) {
        System.out.print(n.s);
    }
}
