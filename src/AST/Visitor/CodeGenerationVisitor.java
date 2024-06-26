package AST.Visitor;

import AST.*;
import Semantics.*;
import java.io.PrintStream;
import java.util.Map;
import java.util.HashMap;

public class CodeGenerationVisitor implements Visitor {
    SymbolTable symTable;
    ClassType currentClass = Bottom.get();
    MethodType currentMethod = Bottom.get();
    PrintStream out = System.out;
    Map<String, Integer> labels = new HashMap<String, Integer>();
    private final boolean DEBUG = false; // Turn this on to clobber transients in the method epilogue

    public CodeGenerationVisitor(SymbolTable s) {
        symTable = Top.symTable = s;
    }

    public String labelgenerator(String s) {
        s = (currentClass instanceof ScopedType t ? t.name() + "$" : "") + (currentMethod instanceof ScopedType t ? t.name() + "_" : "") + s;
        int count = labels.getOrDefault(s, 0);
        labels.put(s, ++count);
        return s + count;
    }

    // Tries to find the String in scope, complains if it can't be found and it
    // hasn't
    // yet been seen in this scope.
    private InstanceType findInScope(String id, ASTNode n) {
        InstanceType var = currentMethod.getVariable(id);
        if (var != Bottom.get())
            return var;
        var = currentMethod.getParam(id);
        if (var != Bottom.get())
            return var;
        var = currentClass.getField(id);
        if (var != Bottom.get())
            return var;
        // var is a _|_, so we now have to implement some logic.
        // If the current class isn't a DeclaredClass, then return.
        if (!(currentClass instanceof ScopedType c))
            return var;

        // If we've seen the unrecognized identifier already in the class,
        // return.
        if (c.seenUnrecognized(id)) {
            symTable.err();
            return var;
        }
        // id is a new unrecognized identifier for the class c.
        String err = "Unrecognized identifier '" + id + "' in " + c.name();
        // Bottom isn't a ScopedType, so we're testing if it's a MainMethod or Method
        if (currentMethod instanceof ScopedType m && !m.seenUnrecognized(id)) {
            // id is a new unrecognized identifier for method m.
            symTable.err(err + "::" + m.name(), n);
            // So we add the unrecognized identifier for method m.
            m.addUnrecognized(id);
        } else if (currentMethod instanceof ScopedType) {
            // We're inside a method & we've seen the unrecognized identifier id for
            // that method.
            symTable.err();
        } else {
            // We're not inside a method -- we're just in the class, so add the id
            // to the class's set of unrecognized identifiers.
            symTable.err(err, n);
            c.addUnrecognized(id);
        }
        return var;
    }

    public InstanceType findInScope(Identifier id) {
        return findInScope(id.s, id);
    }

    public InstanceType findInScope(IdentifierType id) {
        return findInScope(id.s, id);
    }

    public InstanceType findInScope(IdentifierExp id) {
        return findInScope(id.s, id);
    }

    @Override
    public void visit(Program n) {
        out.println("# Vtables");
        out.println(".data");
        for (String key : symTable.classes().keySet()) {
            if (!(symTable.get(key) instanceof DeclaredClass c))
                continue;
            out.format("%s$$:\n", key);
            out.println(!(c.superclass() instanceof DeclaredClass s) ? "\t.quad 0"
                    : String.format("\t.quad %s$$", s.name()));
            for (String method : c.vtable().keySet()) {
                out.format("\t.quad %s$%s\n", c.vtable().get(method), method);
            }
        }
        out.println();
        out.println(".text");
        out.println("\t.globl asm_main");
        n.m.accept(this);
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
        out.println("\tmovq %rbp, %rsp\t\t# epilogue - return");
        out.println("\tpopq %rbp\t\t");
        if (DEBUG) clobber_transients();
        out.println("\tret\n");
    }

    @Override
    public void visit(ClassDeclSimple n) {
        currentClass = symTable.get(n.i);
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.get(i).accept(this);
        }
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(ClassDeclExtends n) {
        currentClass = symTable.get(n.i);
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.get(i).accept(this);
        }
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(VarDecl n) {
        out.println("\tsubq $8, %rsp");
        out.println("\tmovq $0, (%rsp)");
    }

    @Override
    public void visit(MethodDecl n) {
        if (!(currentClass instanceof DeclaredClass cl))
            return;
        MethodType m = cl.getMethod(n.i);
        currentMethod = m;
        out.format("%s$%s:\n", cl.name, ((Method) m).name);
        out.println("\tpushq %rbp");
        out.println("\tmovq %rsp, %rbp");
        for (int i = 0; i < n.vl.size(); i++) {
            n.vl.get(i).accept(this);
        }
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.get(i).accept(this);
        }
        n.e.accept(this);
        out.println("\tmovq %rbp,%rsp\t\t# epilogue - return");
        out.println("\tpopq %rbp\t\t");
        if (DEBUG) clobber_transients();
        out.println("\tret\n");
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
        out.println(labelgenerator("if") + ":");
        n.e.accept(this);
        String jmplabel = labelgenerator("else");
        String afterlabel = labelgenerator("endif");
        out.println("\n\tcmpq $0, %rax");
        out.format("\tjz %s\n", jmplabel);
        n.s1.accept(this);
        out.format("\tjmp %s\n", afterlabel);
        out.format("%s:\n", jmplabel);
        n.s2.accept(this);
        out.format("%s:\n", afterlabel);
    }

    @Override
    public void visit(While n) {
        String bodylabel = labelgenerator("while_body"),
            testlabel = labelgenerator("while_test");
        out.println(labelgenerator("while") + ":");
        out.format("\tjmp %s\n", testlabel);
        out.format("%s:\n", bodylabel);
        n.s.accept(this);
        out.format("%s:\n", testlabel);
        n.e.accept(this);
        out.println("\tcmpq $1, %rax"); // If rax == 1, then jump
        out.format("\tjz %s\n", bodylabel);
    }

    @Override
    public void visit(Print n) {
        pushregs();
        n.e.accept(this);
        out.print("\tmovq %rax, %rdi\t\t# Move expression to first argument register\n");

        out.println("\tcall put\t\t# Method in C file");
        popregs();
    }

    @Override
    public void visit(Assign n) {
        n.e.accept(this);
        out.format("\tmovq %%rax, %s\n", getLocation(n.i.s));
    }

    @Override
    public void visit(ArrayAssign n) {
        n.e1.accept(this); // Gives us the index in %rax
        out.println("\tpushq %rax");
        n.e2.accept(this); // Now contains the value to be assigned
        out.println("\tmovq %rax, %r11"); // Move the value to be assigned so we can get the array itself
        out.println("\tpopq %r10"); // %r10 now contains the index
        out.println("\taddq $1, %r10"); // Account for length at beginning
        out.format("\tmovq %s, %%rax\n", getLocation(n.i.s)); // Move the array into %rax

        out.println("\tcmpq (%rax), %r10");
        out.println("\tleaq (%rax, %r10, 8), %rax"); // lea doesn't alter flags, now offset in %rax
        out.format("\tmovq $%d, %%r10\n", n.line_number); // Now %r10 is free, so can move there for cmovg
        out.println("\tcmovgq %r10, %rdi");
        out.println("\tjg arrayboundserror"); // This is a jump because we're not ever coming back here

        out.println("\tmovq %r11, (%rax)");
    }

    @Override
    public void visit(And n) {
        String label = labelgenerator("end_and");
        n.e1.accept(this);
        out.println("\ttestq %rax, %rax");
        out.println("\tmovq $0, %r11");
        out.println("\tcmovzq %r11, %rax");
        out.println("\tjz " + label);
        out.println("\tpushq %rax");
        n.e2.accept(this);
        out.format("\tpopq %%r11\n");
        out.format("\tandq %%r11, %%rax\n");
        out.println(label + ":");
    }

    @Override
    public void visit(LessThan n) {
        n.e1.accept(this);
        out.println("\tmovq %rax, %r12");
        // a in r12
        n.e2.accept(this);
        // b in %rax
        // test if a - b < 0 === a < b
        out.println("\tcmpq %rax, %r12");
        // mov does not affect flags
        out.println("\tmovq $0, %rax");
        out.println("\tmovq $1, %r12");
        // cmovl moves src to dst if result of previous comparison was less than 0
        out.println("\tcmovlq %r12, %rax");
    }

    @Override
    public void visit(Plus n) {
        n.e1.accept(this);
        out.format("\tpushq %%rax\n");
        n.e2.accept(this);
        out.format("\tpopq %%r11\n");
        out.format("\taddq %%r11, %%rax\n");
    }

    @Override
    public void visit(Minus n) {
        n.e1.accept(this);
        out.println("\tpushq %rax");
        n.e2.accept(this);
        out.println("\tpopq %r11");
        out.println("\tsubq %rax, %r11");
        out.println("\tmovq %r11, %rax");
    }

    @Override
    public void visit(Times n) {
        n.e1.accept(this);
        out.format("\tpushq %%rax\n");
        n.e2.accept(this);
        out.format("\tpopq %%r11\n");
        out.format("\timulq %%r11, %%rax\n");
    }

    @Override
    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        out.println("\tpushq %rax");
        n.e2.accept(this); // %rax now contains the array index we are looking for
        out.println("\taddq $1, %rax"); // Account for length
        out.println("\tpopq %r11"); // %r11 now contains the array itself
        // rax - (r11) > 0 -> rax = index + 1 > (r11) = length
        out.println("\tcmpq (%r11), %rax");
        out.format("\tmovq $%d, %%r10\n", n.line_number);
        out.println("\tcmovgq %r10, %rdi");
        out.println("\tjg arrayboundserror"); // This is a jump because we're not ever coming back here

        out.println("\tmovq (%r11,%rax,8), %rax");
    }

    @Override
    public void visit(ArrayLength n) {
        n.e.accept(this);
        out.println("\tmovq (%rax), %rax");
    }

    @Override
    public void visit(Call n) {
        // check whether identifier is in the symbol table
        pushregs();
        n.e.accept(this);
        out.println("\tpushq %rax"); // Push the object to call on
        for (int i = 0; i < n.el.size(); i++) {
            n.el.get(i).accept(this);
            out.println("\tpushq %rax");
        }
        for (int i = n.el.size(); i >= 0; i--) {
            out.format("\tpopq %s\n", argument_registers[i]);
        }
        out.println("\tmovq (%rdi), %rax"); // Moves the object itself into %rax
        if (!(n.e.expType instanceof Ref r && symTable.get(r.s) instanceof DeclaredClass c))
            throw new RuntimeException("Calling method on non-reference type");
        if (c.vtable().position(n.i.s) == -1) {
            throw new RuntimeException(n.i.s);
        }
        out.format("\tcall *%d(%%rax)\n", 8 * (c.vtable().position(n.i.s) + 1));
        popregs();
    }

    @Override
    public void visit(IntegerLiteral n) {
        out.format("\tmovq $%d, %%rax\n", n.i);
    }

    @Override
    public void visit(True n) {
        out.println("\tmovq $1, %rax");
    }

    @Override
    public void visit(False n) {
        out.println("\tmovq $0, %rax");
    }

    @Override
    public void visit(IdentifierExp n) {
        out.format("\tmovq %s, %%rax\n", getLocation(n.s));
    }

    @Override
    public void visit(This n) {
        out.println("\tmovq %rdi, %rax");
    }

    @Override
    public void visit(NewArray n) {
        n.e.accept(this);
        out.println("\tpushq %rax"); // Number of elements
        out.println("\taddq $1, %rax\t\t# For storing the length");
        out.println("\tshlq $3, %rax\t\t# convert length to required nr of bytes");
        // Length in bytes in %rax
        heapalloc("%rax");
        // Pointer is now in %rax
        out.println("\tpopq %r11"); // Pop number of elements into r11, temporary
        out.println("\tmovq %r11, (%rax)"); // Load length into r11
        // "return" the pointer in %rax
    }

    @Override
    public void visit(NewObject n) {
        if (!(n.expType instanceof Ref r)) {
            throw new RuntimeException("Cannot instantiate primitive type.");
        }
        if (!(symTable.get(r.s) instanceof DeclaredClass c)) {
            throw new RuntimeException("Unrecognized reference type.");
        }
        heapalloc("$" + (8 * (1 + c.fields().size())));
        //out.format("\tleaq %s$$(%%rip), %%r8\n", c.name);
        out.format("\tleaq %s$$, %%r8\n", c.name);
        out.println("\tmovq %r8, (%rax)");
    }

    @Override
    public void visit(Not n) {
        n.e.accept(this);
        out.println("\txorq $1, %rax");
    }

    @Override
    public void visit(Identifier n) {
    }

    // Allocates i bytes, moving the result into %rax.
    private void heapalloc(String i) {
        pushregs();
        out.format("\tmovq %s, %%rdi\n", i);
        out.println("\tcall mjcalloc\t\t# Moves pointer into %rax");
        popregs();
    }

    private void pushregs() {
        out.println();
        for (int i = 0; i <= currentMethod.params(); i++) {
            out.println("\tpushq " + argument_registers[i]);
        }
        out.println();
    }

    private void popregs() {
        out.println();
        for (int i = currentMethod.params(); i >= 0; i--) {
            out.println("\tpopq " + argument_registers[i]);
        }
        out.println();
    }

    private String[] argument_registers = { "%rdi", "%rsi", "%rdx", "%rcx", "%r8", "%r9" };

    private String getLocation(String id) {
        if (!(currentMethod instanceof Method m))
            throw new RuntimeException("Identifier in main method");
        if (m.getParam(id) != Bottom.get()) {
            return argument_registers[m.parameters.position(id) + 1];
        } else if (m.getVariable(id) != Bottom.get()) {
            // The plus one here is rather dubious -- it seems like %%(rbp) is actually the old rsp,
            // so we need to add one for all further positions down.
            return String.format("-%d(%%rbp)", 8 * (m.variables.position(id) + 1));
        } else {
            if (!(currentClass instanceof DeclaredClass c))
                throw new RuntimeException("Identifier in main method");
            int pos = c.fields().get(id);
            return String.format("%d(%%rdi)", 8 * (pos + 1)); // Account for vtable in the first position
        }
    }

    private void clobber_transients() {
        String debugval = "$0xBADBAD";
        for (String arg_reg : argument_registers) {
            out.format("\tmovq %s, %s\n", debugval, arg_reg);
        }
        out.format("\tmovq %s, %%r10\n", debugval);
        out.format("\tmovq %s, %%r11\n", debugval);
    }
}
