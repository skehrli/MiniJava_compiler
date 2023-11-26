package AST.Visitor;

import AST.*;
import Semantics.*;
import Semantics.Type;
import java.io.PrintStream;

public class CodeGenerationVisitor implements Visitor {
    SymbolTable symTable;
    ClassType currentClass = Bottom.get();
    MethodType currentMethod = Bottom.get();
    PrintStream out = System.out;

    public CodeGenerationVisitor(SymbolTable s) {
        symTable = Top.symTable = s;
    }

    @Override
    public void visit(Program n) {
        out.format("\t.text\n");
        out.format("\t.globl asm_main\n");
        n.m.accept(this);
        // declare vtables somehow...
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.get(i).accept(this);
        }
    }

    @Override
    public void visit(MainClass n) {
        currentClass = symTable.get(n.i1);
        currentMethod = currentClass.getMethod("main");
        
        out.format("asm_main:\t\t# entry point of program\n");
        n.s.accept(this);
    }

    @Override
    public void visit(ClassDeclSimple n) {
        currentClass = symTable.get(n.i);
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(ClassDeclExtends n) {
        currentClass = symTable.get(n.i);
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(VarDecl n) {
    }

    @Override
    public void visit(MethodDecl n) {
        if (!(currentClass instanceof DeclaredClass cl))
            return;
        MethodType m = cl.getMethod(n.i);
        if (m == Bottom.get())
            return;
        currentMethod = m;
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.get(i).accept(this);
        }
        n.e.accept(this);
    }

    @Override
    public void visit(Formal n) {
    }

    @Override
    public void visit(IntArrayType n) {

    }

    @Override
    public void visit(BooleanType n) {

    }

    @Override
    public void visit(IntegerType n) {
    }

    @Override
    public void visit(IdentifierType n) {
    }

    @Override
    public void visit(Block n) {
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.get(i).accept(this);
        }
    }

    @Override
    public void visit(If n) {
        n.e.accept(this);
        n.s1.accept(this);
        n.s2.accept(this);
    }

    @Override
    public void visit(While n) {
        n.e.accept(this);
        n.s.accept(this);
    }

    @Override
    public void visit(Print n) {
        n.e.accept(this);
        out.format("\tmovq %rax, %rdi\n\t\t# Move expression to first argument register");
        out.format("\tcall put\t\t# Method in C file");
    }

    @Override
    public void visit(Assign n) {
        n.e.accept(this);
        
    }

    @Override
    public void visit(ArrayAssign n) {
        n.e1.accept(this);
        n.e2.accept(this);
        
    }

    @Override
    public void visit(And n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(LessThan n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(Plus n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(Minus n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(Times n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(ArrayLength n) {
        n.e.accept(this);

    }

    @Override
    public void visit(Call n) {
        // check whether identifier is in the symbol table
        n.e.accept(this);
        InstanceType callerType = n.e.expType;
        if (!(callerType instanceof Ref caller)) {
            symTable.err(String.format("LHS of call is not a reference type; has type %s.", n.e.expType), n);
            n.expType = Semantics.Bottom.get();
            return;
        }
        MethodType method = symTable.get(caller.s).getMethod(n.i.toString());
        if (!(method instanceof Method m)) {
            symTable.err(String.format("%s not a valid method for Type %s.", n.i, n.e.expType), n);
            n.expType = Semantics.Bottom.get();
            return;
        }
        // verify whether all parameters have the same type
        for (int i = 0; i < n.el.size(); i++) {
            n.el.get(i).accept(this);
        }
        if (n.el.size() != m.parameters.size()) {
            symTable.err("Wrong number of arguments.", n.el);
        }
    }

    @Override
    public void visit(IntegerLiteral n) {
        out.format("\tmovq %d, %rax\n", n.i);
    }

    @Override
    public void visit(True n) {
    }

    @Override
    public void visit(False n) {
    }

    @Override
    public void visit(IdentifierExp n) {
    }

    @Override
    public void visit(This n) {
    }

    @Override
    public void visit(NewArray n) {
        n.e.accept(this);
    }

    @Override
    public void visit(NewObject n) {

    }

    @Override
    public void visit(Not n) {
        n.e.accept(this);
    }

    @Override
    public void visit(Identifier n) {
    }
}
