package AST.Visitor;

import java.util.LinkedHashMap;

import AST.*;
import Semantics.*;

public class ExpressionTypeVisitor implements Visitor {
    LinkedHashMap<String, ClassType> symTable;
    ClassType currentClass;
    Method currentMethod;

    public ExpressionTypeVisitor(LinkedHashMap<String, ClassType> s) {
        symTable = s;
    }

    public Semantics.InstanceType findInScope(String id) {
        InstanceType var = Bottom.get();
        boolean isMainClass = currentClass instanceof MainClassType;
        if (currentMethod != null && currentMethod.parameterTypes.containsKey(id)) {
            var = currentMethod.parameterTypes.get(id);
        } else if (currentMethod != null && currentMethod.variables.containsKey(id)) {
            var = currentMethod.variables.get(id);
        } else if (!isMainClass) {
            DeclaredClass curClass = (DeclaredClass) currentClass;
            if (curClass.instances.containsKey(id)) {
                var = curClass.instances.get(id);
            }
        }
        return var;
    }

    public boolean assignmentCompatible(InstanceType a, InstanceType b) {
        if (a == Bottom.get() || b == Bottom.get()) {
            return true;
        }
        if (a instanceof Ref && b instanceof Ref) {
            ClassType c1 = ((Ref) a).c;
            ClassType c2 = ((Ref) b).c;
            while (c1 != c2 && c2 instanceof DeclaredClass && ((DeclaredClass) c2).superclass != null) {
                c2 = ((DeclaredClass) c2).superclass;
            }
            return c1 == c2;
        } else {
            return a == b;
        }
    }

    public boolean sameType(Semantics.Type a, Semantics.Type b) {
        // either they are the same singleton type or they are a ref
        // of the same class
        if (a == b)
            return true;
        if (!(a instanceof Ref && b instanceof Ref))
            return false;
        Ref a_ref = (Ref) a;
        Ref b_ref = (Ref) b;
        return a_ref.c == b_ref.c;
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
        if (m == Bottom.get()) return;
        currentMethod = (Method) m;
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.get(i).accept(this);
        }
        n.e.accept(this);
        if (!sameType(n.e.expType, currentMethod.getReturn())) {
            System.err.format("Line %d: Wrong return type: Expected %s but is %s.\n", n.e.line_number,
                    currentMethod.getReturn(), n.e.expType);
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
        Semantics.Type t = n.e.expType;
        if (t != Semantics.Boolean.get() && t != Semantics.Bottom.get()) {
            System.err.format("Line %d: Expression in if-statement not Boolean.\n", n.e.line_number);
        }
        n.s1.accept(this);
        n.s2.accept(this);
    }

    @Override
    public void visit(While n) {
        n.e.accept(this);
        Semantics.Type t = n.e.expType;
        if (t != Semantics.Boolean.get() && t != Semantics.Bottom.get()) {
            System.err.format("Line %d: Expression in while-statement not Boolean.\n", n.e.line_number);
        }
        n.s.accept(this);
    }

    @Override
    public void visit(Print n) {
        n.e.accept(this);
    }

    @Override
    public void visit(Assign n) {
        n.e.accept(this);
        InstanceType lhs = findInScope(n.i.toString());
        if (!assignmentCompatible(lhs, n.e.expType)) {
            System.err.format("Line %d: Cannot assign expression of type %s to variable of type %s.\n", n.e.line_number,
                    n.e.expType.toString(), lhs.toString());
        }
    }

    @Override
    public void visit(ArrayAssign n) {
        n.e1.accept(this);
        n.e2.accept(this);
        InstanceType t = findInScope(n.i.toString());
        if (t != Semantics.Array.get()) {
            System.err.format("Line %d: %s not of type array.\n", n.line_number, n.i.toString());
        }
        if (n.e1.expType != Semantics.Int.get() && n.e1.expType != Bottom.get()) {
            System.err.format("Line %d: Array is not indexed with an int.\n", n.e1.line_number);
        }
        if (!assignmentCompatible(n.e1.expType, Semantics.Int.get())) {
            System.err.format("Line %d: Right-hand side of assignment must be int but is of type %s.\n",
                    n.e2.line_number, n.e2.expType.toString());
        }
    }

    @Override
    public void visit(And n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Boolean.get() || n.e2.expType != Semantics.Boolean.get()) {
            System.err.format("Line %d: Operands of && must be of type boolean, boolean but are type %s, %s.\n",
                    n.e1.line_number, n.e1.expType.toString(), n.e2.expType.toString());
        }
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(LessThan n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            System.err.format("Line %d: Operands of '<' must be of type Int, Int but are type %s, %s.\n",
                    n.e1.line_number, n.e1.expType.toString(), n.e2.expType.toString());
        }
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(Plus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            System.err.format("Line %d: Operands of '+' must be of type Int, Int but are type %s, %s.\n",
                    n.e1.line_number, n.e1.expType.toString(), n.e2.expType.toString());
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(Minus n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            System.err.format("Line %d: Operands of '-' must be of type Int, Int but are type %s, %s.\n",
                    n.e1.line_number, n.e1.expType.toString(), n.e2.expType.toString());
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(Times n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Int.get() || n.e2.expType != Semantics.Int.get()) {
            System.err.format("Line %d: Operands of '*' must be of type Int, Int but are type %s, %s.\n",
                    n.e1.line_number, n.e1.expType.toString(), n.e2.expType.toString());
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(ArrayLookup n) {
        n.e1.accept(this);
        n.e2.accept(this);
        if (n.e1.expType != Semantics.Array.get() && n.e1.expType != Bottom.get()) {
            System.err.format("Line %d: Expected type array, but is type %s.\n",
                    n.e1.line_number, n.e1.expType.toString());
        }
        if (n.e2.expType != Semantics.Int.get() && n.e2.expType != Bottom.get()) {
            System.err.format("Line %d: Array must be indexed with int, but found %s.\n",
                    n.e2.line_number, n.e2.expType.toString());
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(ArrayLength n) {
        n.e.accept(this);
        if (n.e.expType != Array.get() && n.e.expType != Bottom.get()) {
            System.err.format("Line %d: Expression must be of type Array, but found %s.\n", n.e.line_number,
                    n.e.expType);
        }
        n.expType = Semantics.Int.get();
    }

    @Override
    public void visit(Call n) {
        // check whether identifier is in the symbol table
        n.e.accept(this);
        InstanceType callerType = n.e.expType;
        if (callerType instanceof Semantics.Ref) {
            MethodType method = ((Semantics.Ref) callerType).c.getMethod(n.i.toString());
            if (method == Bottom.get()) {
                System.err.format("Line %d: %s not a valid method for Type %s.\n", n.line_number, n.i.toString(),
                        n.e.expType);
                n.expType = Semantics.Bottom.get();
            } else {
                // verify whether all parameters have the same type
                Method m = (Method) method;
                for (int i = 0; i < n.el.size(); i++) {
                    n.el.get(i).accept(this);
                }
                if (n.el.size() != m.parameterTypes.size()) {
                    System.err.format("Line %d: Wrong number of arguments.\n", n.el.line_number);
                }
                int i = 0;
                boolean parameters_match = true;
                for (InstanceType t : m.parameterTypes.values()) {
                    if (!assignmentCompatible(t, n.el.get(i).expType)) {
                        System.err.format("Line %d: Wrong argument type of %s: Type %s instead of %s.\n",
                                n.el.get(i).line_number,
                                n.el.get(i).toString(), n.el.get(i).expType, t);
                        parameters_match = false;
                        break;
                    }
                    i++;
                }
                n.expType = parameters_match ? m.getReturn() : Bottom.get();
            }
        } else {
            System.err.format("Line %d: LHS of call is not a reference type; has type %s.\n", n.line_number, n.e.expType);
            n.expType = Semantics.Bottom.get();
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
        n.expType = new Ref((DeclaredClass) currentClass);
    }

    @Override
    public void visit(NewArray n) {
        n.e.accept(this);
        Semantics.Type t = n.e.expType;
        if (t != Semantics.Int.get() && t != Semantics.Bottom.get()) {
            System.err.format("Line %d: Array length must be of type int, but found %s.\n", n.line_number, n.e.expType);
        }
        n.expType = Semantics.Array.get();
    }

    @Override
    public void visit(NewObject n) {
        ClassType cl = symTable.get(n.i.toString());
        if (cl == null) {
            System.err.format("Line %d: Class %s not found.\n", n.line_number, n.i.toString());
            n.expType = Bottom.get();
        } else if (cl instanceof MainClassType) {
            System.err.format("Line %d: Cannot instantiate main class.\n", n.line_number);
            System.exit(1);
        } else {
            n.expType = new Ref(symTable.get(n.i.toString()));
        }
    }

    @Override
    public void visit(Not n) {
        n.e.accept(this);
        Semantics.Type t = n.e.expType;
        if (t != Bottom.get() && t != Semantics.Boolean.get()) {
            System.err.format("Line %d: Expression must be of type Boolean.\n", n.e.line_number);
        }
        n.expType = Semantics.Boolean.get();
    }

    @Override
    public void visit(Identifier n) {

    }
}
