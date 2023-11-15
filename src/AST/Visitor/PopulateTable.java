package AST.Visitor;

import java.util.LinkedHashMap;

import AST.*;
import Semantics.*;

public class PopulateTable implements Visitor {
    LinkedHashMap<String, ClassType> symTable;
    DeclaredClass currentClass;
    Method currentMethod;

    public InstanceType convertType(ASTNode astType) {
        if (astType instanceof IntegerType) {
            return Int.get();
        } else if (astType instanceof BooleanType) {
            return Semantics.Boolean.get();
        } else if (astType instanceof IntArrayType) {
            return Array.get();
        } else if (astType instanceof IdentifierType) {
            return new Ref((DeclaredClass) symTable.get(((IdentifierType) astType).s));
        } else {
            return Bottom.get();
        }
    }

    public PopulateTable(LinkedHashMap<String, ClassType> s) {
        symTable = s;
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
    }

    @Override
    public void visit(ClassDeclSimple n) {
        DeclaredClass cl = (DeclaredClass) symTable.get(n.i.toString());
        currentClass = cl;
        for (int i = 0; i < n.vl.size(); i++) {
            String fieldName = n.vl.get(i).i.toString();
            if (cl.methods.containsKey(fieldName)) {
                System.err.format("Line %d: Fields must have unique names.\n", n.line_number);
            } else {
                cl.addField(fieldName, convertType(n.vl.get(i).t));
            }
        }
        for (int i = 0; i < n.ml.size(); i++) {
            Method method = new Method(convertType(n.ml.get(i).t));
            String methodName = n.ml.get(i).i.toString();
            if (cl.methods.containsKey(methodName)) {
                System.err.format("Line %d: Methods must have unique names. Overloading not allowed\n", n.line_number);
            } else {
                DeclaredClass superclass = cl.superclass;
                while (superclass != null) {
                    if (superclass.methods.containsKey(methodName)) {
                        // check if equal signature
                        Method superClassMethod = (Method) superclass.methods.get(methodName);
                        if (superClassMethod.parameterTypes.size() != method.parameterTypes.size()) {
                            System.err.format(
                                    "Line %d: Method overrides a superclass method, but with wrong signature\n",
                                    n.line_number);

                        }
                        InstanceType[] params1 = (InstanceType[]) method.parameterTypes.values().toArray();
                        InstanceType[] params2 = (InstanceType[]) superClassMethod.parameterTypes.values().toArray();
                        for (int j = 0; j < superClassMethod.parameterTypes.size(); j++) {
                            if (params1[i] != params2[i]) {
                                if (params1[i] instanceof Ref && params2[i] instanceof Ref
                                        && ((Ref) params1[i]).c == ((Ref) params2[i]).c) {
                                    continue;
                                }
                                System.err.format(
                                        "Line %d: Method overrides a superclass method, but with wrong signature\n",
                                        n.line_number);
                            }
                        }
                    }
                }
                currentMethod = method;
                n.ml.get(i).accept(this);
                cl.addMethod(n.ml.get(i).i.toString(), method);
            }
        }
    }

    @Override
    public void visit(ClassDeclExtends n) {
        System.err.println(n.i.toString());
        symTable.keySet().forEach(System.out::println);
        DeclaredClass cl = (DeclaredClass) symTable.get(n.i.toString());
        currentClass = cl;
        for (int i = 0; i < n.vl.size(); i++) {
            String fieldName = n.vl.get(i).i.toString();
            if (cl.methods.containsKey(fieldName)) {
                System.err.format("Line %d: Fields must have unique names.\n", n.line_number);
            } else {
                cl.addField(fieldName, convertType(n.vl.get(i).t));
            }
        }
        for (int i = 0; i < n.ml.size(); i++) {
            Method method = new Method(convertType(n.ml.get(i).t));
            currentMethod = method;
            n.ml.get(i).accept(this);
            if (!cl.addMethod(n.ml.get(i).i.toString(), method))
                System.err.format("Line %d: Methods must have unique names. Overloading not allowed\n",
                        n.ml.get(i).line_number);
        }
    }

    @Override
    public void visit(VarDecl n) {
    }

    @Override
    public void visit(MethodDecl n) {
        for (int i = 0; i < n.fl.size(); i++) {
            if (!currentMethod.addParam(n.fl.get(i).i.toString(), convertType(n.fl.get(i).t))) {
                System.err.format("Line %d: Parameters must have unique names.\n",
                        n.fl.get(i).line_number);
            }
        }
        for (int i = 0; i < n.vl.size(); i++) {
            if (!currentMethod.addVariable(n.vl.get(i).i.toString(), convertType(n.vl.get(i).t)))
                System.err.format("Line %d: %s declared previously.\n",
                        n.vl.get(i).line_number, n.vl.get(i).i.toString());
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
