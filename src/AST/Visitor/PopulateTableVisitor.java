package AST.Visitor;

import AST.*;
import Semantics.*;
import Semantics.Type;

import java.util.Iterator;

public class PopulateTableVisitor implements Visitor {
    SymbolTable symTable;
    DeclaredClass currentClass;
    Method currentMethod;

    public InstanceType convertType(ASTNode astType) {
        if (astType instanceof IntegerType) {
            return Int.get();
        } else if (astType instanceof BooleanType) {
            return Semantics.Boolean.get();
        } else if (astType instanceof IntArrayType) {
            return Array.get();
        } else if (astType instanceof IdentifierType id) {
            // return Ref if Class exists and Bottom else
            return symTable.get(id.s) == Bottom.get() ? Bottom.get() : new Ref(id.s);
        } else {
            return Bottom.get();
        }
    }

    public PopulateTableVisitor(SymbolTable s) {
        symTable = Top.symTable = s;
    }

    @Override
    public void visit(Program n) {
        new ResolveClasses(symTable).resolve(n);
        n.m.accept(this);
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.get(i).accept(this);
        }
    }

    @Override
    public void visit(MainClass n) {
    }

    @Override
    public void visit(ClassDeclSimple n) {
        DeclaredClass cl = (DeclaredClass) symTable.get(n.i);
        currentClass = cl;
        for (int i = 0; i < n.vl.size(); i++) {
            VarDecl field = n.vl.get(i);
            if (!cl.addField(field.i, convertType(field.t))) {
                symTable.err("Fields must have unique names.", n);
            }
        }
        for (int i = 0; i < n.ml.size(); i++) {
            MethodDecl m = n.ml.get(i);
            Method method = new Method(m.i, convertType(m.t));
            if (!cl.addMethod(m.i, method)) {
                symTable.err("Methods must have unique names. Overloading not allowed.", n);
                continue;
            }
            currentMethod = method;
            n.ml.get(i).accept(this);
        }
    }

    @Override
    public void visit(ClassDeclExtends n) {
        DeclaredClass cl = (DeclaredClass) symTable.get(n.i);
        currentClass = cl;
        for (int i = 0; i < n.vl.size(); i++) {
            VarDecl field = n.vl.get(i);
            if (!cl.addField(field.i, convertType(field.t))) {
                symTable.err("Fields must have unique names.", n);
            }
        }

        for (int i = 0; i < n.ml.size(); i++) {
            MethodDecl m = n.ml.get(i);
            Method method = new Method(m.i, convertType(m.t));
            if (!cl.addMethod(m.i, method)) {
                symTable.err("Methods must have unique names. Overloading not allowed.", n);
                continue;
            }
            currentMethod = method;
            n.ml.get(i).accept(this);

            if (cl.superclass() == Bottom.get())
                continue;
            overrideCorrect(m.i);
        }
    }

    private boolean overrideCorrect(Identifier methodName) {
        if (!(currentClass.getMethod(methodName) instanceof Method method)) {
            return true;
        }
        ClassType superclass = currentClass.superclass();
        while (superclass != Top.get()) {
            MethodType superMethodOpt = superclass.getMethod(methodName);
            if (superMethodOpt.params() != method.params()) {
                symTable.err("Parameters in " + currentClass.name + "::" + methodName + " do not match superclass parameters; no overloading allowed.", methodName);
                return false;
            }
            if (!(superMethodOpt instanceof Method superMethod)) {
                superclass = superclass.superclass();
                continue;
            }
            Iterator<InstanceType> sit = superMethod.parameters.values().iterator(),
                    it = method.parameters.values().iterator();
            for (int j = 0; j < superMethod.params(); j++) {
                // Contravariance is not allowed, that's just overloading
                if (!Type.sameType(sit.next(), it.next())) {
                    symTable.err("Parameters in " + currentClass.name + "::" + methodName + " do not match superclass parameters; no overloading allowed.", methodName);
                    return false;
                }
            }
            // Require covariance in return types
            if (!Type.assignmentCompatible(superMethod.getReturn(), method.getReturn())) {
                symTable.err("Return type of overriding method " + currentClass.name + "::" + methodName + " is incompatible with superclass return type.", methodName);
                return false;
            }
            superclass = superclass.superclass();
        }
        return true;
    }

    @Override
    public void visit(VarDecl n) {
    }

    @Override
    public void visit(MethodDecl n) {
        for (int i = 0; i < n.fl.size(); i++) {
            Formal formal = n.fl.get(i);
            InstanceType argumentType = convertType(formal.t);
            if (argumentType == Bottom.get()) {
                symTable.err("Invalid argument type for: " + formal.i.toString(), formal);
            }
            if (!currentMethod.addParam(formal.i, convertType(formal.t))) {
                symTable.err("Parameters must have unique names.", formal);
            }
        }
        for (int i = 0; i < n.vl.size(); i++) {
            VarDecl var = n.vl.get(i);
            if (!currentMethod.addVariable(var.i, convertType(var.t)))
                symTable.err(var.i + " declared previously.", var);
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

    }

    @Override
    public void visit(If n) {

    }

    @Override
    public void visit(While n) {

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

    }

    @Override
    public void visit(LessThan n) {

    }

    @Override
    public void visit(Plus n) {

    }

    @Override
    public void visit(Minus n) {

    }

    @Override
    public void visit(Times n) {

    }

    @Override
    public void visit(ArrayLookup n) {

    }

    @Override
    public void visit(ArrayLength n) {

    }

    @Override
    public void visit(Call n) {

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
