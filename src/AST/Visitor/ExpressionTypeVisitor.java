package AST.Visitor;

import AST.*;
import Semantics.*;
import Semantics.Type;

public class ExpressionTypeVisitor implements Visitor {
    SymbolTable symTable;
    ClassType currentClass;
    Method currentMethod;

    public ExpressionTypeVisitor(SymbolTable s) {
        symTable = s;
    }

    public InstanceType findInScope(String id) {
        InstanceType var = currentMethod.getVariable(id);
        if (var != Bottom.get()) return var;
        var = currentMethod.getParam(id);
        if (var != Bottom.get()) return var;
        var = currentClass.getField(id);
        return var;
    }

    @Override
    public void visit(Program n) {
        n.m.accept(this);
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.get(i).accept(this);
        }
    }

    @Override
    public void visit(MainClass n) {
        currentClass = symTable.get(n.i1.toString());
        n.s.accept(this);
    }

    @Override
    public void visit(ClassDeclSimple n) {
        currentClass = symTable.get(n.i.toString());
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(ClassDeclExtends n) {
        currentClass = symTable.get(n.i.toString());
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(VarDecl n) {
    }

    @Override
    public void visit(MethodDecl n) {
        DeclaredClass cl = (DeclaredClass) currentClass;
        MethodType m = cl.getMethod(n.i.toString());
        if (m == Bottom.get())
            return;
        currentMethod = (Method) m;
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.get(i).accept(this);
        }
        n.e.accept(this);
        if (!Type.assignmentCompatible(currentMethod.getReturn(), n.e.expType)) {
            symTable.err(String.format("Incompatible return type: Expected %s but is %s.",
                    currentMethod.getReturn(), n.e.expType), n);
        }
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
        Type t = n.e.expType;
        if (t != Semantics.Boolean.get() && t != Semantics.Bottom.get()) {
            symTable.err("Expression in if not of boolean type.", n.e);
        }
        n.s1.accept(this);
        n.s2.accept(this);
    }

    @Override
    public void visit(While n) {
        n.e.accept(this);
        Type t = n.e.expType;
        if (t != Semantics.Boolean.get() && t != Semantics.Bottom.get()) {
            symTable.err("Expression in while not of boolean type.", n.e);
        }
        n.s.accept(this);
    }

    @Override
    public void visit(Print n) {
        n.e.accept(this);
        if (n.e.expType != Semantics.Int.get()) {
            symTable.err("Argument of System.out.println not of integer type.", n.e);
        }
    }

    @Override
    public void visit(Assign n) {
        n.e.accept(this);
        InstanceType lhs = findInScope(n.i.toString());
        if (!Type.assignmentCompatible(lhs, n.e.expType)) {
            symTable.err(String.format("Cannot assign expression of type %s to variable of type %s.",
                    n.e.expType, lhs), n);
        }
    }

    @Override
    public void visit(ArrayAssign n) {
        n.e1.accept(this);
        n.e2.accept(this);
        InstanceType t = findInScope(n.i.toString());
        if (t != Semantics.Array.get()) {
            symTable.err(n.i + " not of type array, cannot be indexed.", n);
        }
        if (n.e1.expType != Semantics.Int.get() && n.e1.expType != Bottom.get()) {
            symTable.err("Array indexed must be of integer type.", n.e1);
        }
        if (!Type.assignmentCompatible(n.e1.expType, Semantics.Int.get())) {
            symTable.err("Right-hand side of assignment must be int but is of type" +
                    n.e2.expType + ".", n.e2);
        }
    }

    @Override
    public void visit(And n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Boolean.get() || n.e2.expType != Semantics.Boolean.get()) {
            symTable.err(String.format("Operands of (&&) must both be of type boolean but are of types %s and %s.",
                    n.e1.expType, n.e2.expType), n);
        }
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(LessThan n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            symTable.err(String.format("Operands of (<) must both be of type int but are of types %s and %s.",
                    n.e1.expType, n.e2.expType), n);
        }
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(Plus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            symTable.err(String.format("Operands of (+) must both be of type int but are of types %s and %s.",
                    n.e1.expType, n.e2.expType), n);
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(Minus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            symTable.err(String.format("Operands of (-) must both be of type int but are of types %s and %s.",
                    n.e1.expType, n.e2.expType), n);
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(Times n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            symTable.err(String.format("Operands of (*) must both be of type int but are of types %s and %s.",
                    n.e1.expType, n.e2.expType), n);
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Array.get() && n.e1.expType != Bottom.get()) {
            symTable.err(String.format("Expected type int[], but found type %s.", n.e1.expType), n);
        }
        if (n.e2.expType != Semantics.Int.get() && n.e2.expType != Bottom.get()) {
            symTable.err(String.format("Array must be indexed with int, but found %s.", n.e2.expType), n);
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(ArrayLength n) {
        n.e.accept(this);
        if (n.e.expType != Array.get() && n.e.expType != Bottom.get()) {
            symTable.err(String.format("Expression must be of type int[], but found %s.",n.e.expType), n.e);
        }
        n.expType = Semantics.Int.get();
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
        if (method == Bottom.get()) {
            symTable.err(String.format("%s not a valid method for Type %s.", n.i, n.e.expType), n);
            n.expType = Semantics.Bottom.get();
            return;
        }
        // verify whether all parameters have the same type
        Method m = (Method) method;
        for (int i = 0; i < n.el.size(); i++) {
            n.el.get(i).accept(this);
        }
        if (n.el.size() != m.parameters.size()) {
            symTable.err("Wrong number of arguments.", n.el);
        }
        int i = 0;
        for (InstanceType t : m.parameters.values()) {
            Exp param = n.el.get(i);
            if (!Type.assignmentCompatible(t, param.expType)) {
                symTable.err(String.format("Wrong argument type of %s: Type %s instead of %s.",
                        param, param.expType, t), param);
                n.expType = Bottom.get();
                return;
            }
            i++;
        }
    }

    @Override
    public void visit(IntegerLiteral n) {
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(True n) {
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(False n) {
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(IdentifierExp n) {
        n.expType = findInScope(n.s);
    }

    @Override
    public void visit(This n) {
        n.expType = new Ref(currentClass.toString());
    }

    @Override
    public void visit(NewArray n) {
        n.e.accept(this);
        Semantics.Type t = n.e.expType;
        if (t != Semantics.Int.get() && t != Semantics.Bottom.get()) {
            symTable.err("Array length must be of type int, but found " + n.e.expType + ".", n);
        }
        n.expType = Semantics.Array.get();
    }

    @Override
    public void visit(NewObject n) {
        ClassType cl = symTable.get(n.i.toString());
        if (cl == Bottom.get() || cl == Top.get()) {
            symTable.err("Class " + n.i + " not found.", n);
            n.expType = Bottom.get();
        } else if (cl == MainClassType.get()) {
            symTable.err();
            throw new RuntimeException("Illegal instantiation of main class on line " + n.line_number + ".");
        } else {
            n.expType = new Ref(n.i.toString());
        }
    }

    @Override
    public void visit(Not n) {
        n.e.accept(this);
        Semantics.Type t = n.e.expType;
        if (t != Bottom.get() && t != Semantics.Boolean.get()) {
            symTable.err("Argument to (!) must be of boolean type.", n);
        }
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(Identifier n) {
    }
}
