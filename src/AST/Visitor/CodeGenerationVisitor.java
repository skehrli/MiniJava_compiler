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
        out.println(".data");
        for (String key : symTable.classes().keySet()) {
            if (!(symTable.get(key) instanceof DeclaredClass c)) continue;
            out.format("%s$$:\n", key);
            out.println(!(c.superclass() instanceof DeclaredClass s) ? "\t.quad 0" :String.format("\t.quad %s$$", s.name()));
            for (String method : c.vtable().keySet()) {
                out.format("\t.quad %s$%s\n", c.vtable().get(method), method);
            }
        }
        out.println();
        out.println(".text");
        out.println("\t.globl asm_main");
        n.m.accept(this);
        // declare vtables somehow...
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.get(i).accept(this);
        }
        out.println();
    }

    @Override
    public void visit(MainClass n) {
        currentClass = symTable.get(n.i1);
        currentMethod = currentClass.getMethod("main");
        
        out.println("asm_main:\t\t# entry point of program");
        out.println("\tpushq %rbp\t\t# prologue - save frame ptr");
        out.println("\tmovq %rsp, %rbp\t\t# no local vars - no additional stack");
        n.s.accept(this);
        out.println("\tmovq %rbp,%rsp\t\t# epilogue - return");
        out.println("\tpopq %rbp\t\t");
        out.println("\tret");
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
        out.print("\tmovq %rax, %rdi\t\t# Move expression to first argument register\n");
        out.print("\tcall put\t\t# Method in C file");
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
        out.format("\tmovq $%d, %%rax\n", n.i);
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
