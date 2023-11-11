package AST.Visitor;

import java.util.LinkedHashMap;

import AST.*;
import Semantics.*;

public class ExpressionTypeVisitor implements Visitor {
    LinkedHashMap<String, ClassType> symTable;
    DeclaredClass currentClass;
    Method currentMethod;

    public ExpressionTypeVisitor(LinkedHashMap<String, ClassType> s) {
        symTable = s;
    }

    // public Semantics.Type typeOf(And e) {
    // return Semantics.Boolean.get();
    // }
    //
    // public Semantics.Type typeOf(LessThan e) {
    // return Semantics.Boolean.get();
    // }
    //
    // public Semantics.Type typeOf(True e) {
    // return Semantics.Boolean.get();
    // }
    //
    // public Semantics.Type typeOf(False e) {
    // return Semantics.Boolean.get();
    // }
    //
    // public Semantics.Type typeOf(Plus e) {
    // return Semantics.Int.get();
    // }
    //
    // public Semantics.Type typeOf(Minus e) {
    // return Semantics.Int.get();
    // }
    //
    // public Semantics.Type typeOf(Times e) {
    // return Semantics.Int.get();
    // }
    //
    // public Semantics.Type typeOf(ArrayAssign e) {
    // return Semantics.Int.get();
    // }
    //
    // public Semantics.Type typeOf(ArrayLength e) {
    // return Semantics.Int.get();
    // }
    //
    // public Semantics.Type typeOf(ArrayLookup e) {
    // return Semantics.Int.get();
    // }
    //
    // public Semantics.Type typeOf(Assign e) {
    // return typeOf(e.e);
    // }
    //
    // // TODO: implement all typeOf() methods to find the type of expressions
    //
    // public Semantics.Type typeOf(Call e) {
    // String callerId = e.i.toString();
    // InstanceType caller = currentMethod.variables.get(callerId);
    // if (caller == null) {
    // caller = currentClass.instances.get(callerId);
    // }
    // if (caller == null)
    // return Bottom.get();
    //
    // }

    @Override
    public void visit(Program n) {
        n.m.accept(this);
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.get(i).accept(this);
        }
    }

    @Override
    public void visit(MainClass n) {
        currentClass = null;
        n.s.accept(this);
    }

    @Override
    public void visit(ClassDeclSimple n) {
        currentClass = (DeclaredClass) symTable.get(n.i.toString());
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(ClassDeclExtends n) {
        currentClass = (DeclaredClass) symTable.get(n.i.toString());
        for (int i = 0; i < n.ml.size(); i++) {
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(VarDecl n) {
    }

    @Override
    public void visit(MethodDecl n) {
        for (int i = 0; i < n.sl.size(); i++) {
            n.sl.get(i).accept(this);
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

    }

    @Override
    public void visit(Assign n) {

    }

    @Override
    public void visit(ArrayAssign n) {

    }

    @Override
    public void visit(And n) {
        n.expType = Semantics.Boolean.get();
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(LessThan n) {
        n.expType = Semantics.Boolean.get();
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(Plus n) {
        n.expType = Semantics.Int.get();
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(Minus n) {
        n.expType = Semantics.Int.get();
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(Times n) {
        n.expType = Semantics.Int.get();
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(ArrayLookup n) {
        n.expType = Semantics.Int.get();
        n.e1.accept(this);
        n.e2.accept(this);
    }

    @Override
    public void visit(ArrayLength n) {
        n.expType = Semantics.Int.get();
        n.e.accept(this);
    }

    @Override
    public void visit(Call n) {
        // check whether identifier is in the symbol table
        n.e.accept(this);
        InstanceType caller = null;
        if (currentMethod.variables.containsKey(n.i.toString())) {
            caller = currentMethod.variables.get(n.i.toString());
        } else if (currentClass.instances.containsKey(n.i.toString())) {
            caller = currentClass.instances.get(n.i.toString());
        } else {
            System.err.format("Line %d: %s is undeclared.\n", n.line_number, n.i.toString());
            caller = Semantics.Bottom.get();
        }
        if (caller == Semantics.Bottom.get()) {
        } else if (caller instanceof Semantics.Ref) {
            MethodType method = ((Semantics.Ref) caller).c.methods.get(n.i.toString());
            if (method == null || !(method instanceof Semantics.Method)) {
                System.err.format("Line %d: %s not a valid method for Type %s.\n", n.line_number, n.i.toString(),
                        n.e.expType);
                n.expType = Semantics.Bottom.get();
            } else {
                // verify whether all parameters have the same type
                Method m = (Method) method;
                for (int i = 0; i < m.parameterTypes.size(); i++) {

                }
                // n.expType =
            }
        } else {
            System.err.format("Line %d: %s is not a reference type.\n", n.line_number, n.i.toString());
        }
        n.e.accept(this);
    }

    @Override
    public void visit(IntegerLiteral n) {

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

    }

    @Override
    public void visit(NewObject n) {

    }

    @Override
    public void visit(Not n) {

    }

    @Override
    public void visit(Identifier n) {

    }
}
