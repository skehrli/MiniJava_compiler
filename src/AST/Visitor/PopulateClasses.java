package AST.Visitor;

import java.util.LinkedHashMap;

import AST.*;
import Semantics.*;

public class PopulateClasses implements Visitor {
    LinkedHashMap<String, ClassType> symTable;
    DeclaredClass currentClass;
    Method currentMethod;

    public PopulateClasses(LinkedHashMap<String, ClassType> s) {
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
        symTable.put(n.i1.toString(), MainClassType.get());
    }

    @Override
    public void visit(ClassDeclSimple n) {
        DeclaredClass cl = new DeclaredClass();
        currentClass = cl;
        if (symTable.containsKey(n.i.toString())) {
            System.err.format("Line %d: Classes must have unique names.\n", n.line_number);
        } else {
            symTable.put(n.i.toString(), cl);
        }
    }

    @Override
    public void visit(ClassDeclExtends n) {
    }

    @Override
    public void visit(VarDecl n) {
    }

    @Override
    public void visit(MethodDecl n) {
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
