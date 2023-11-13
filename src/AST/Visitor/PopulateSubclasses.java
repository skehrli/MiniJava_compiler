package AST.Visitor;

import java.util.LinkedHashMap;

import AST.*;
import Semantics.*;

public class PopulateSubclasses implements Visitor {
    LinkedHashMap<String, ClassType> symTable;
    DeclaredClass currentClass;
    Method currentMethod;

    public PopulateSubclasses(LinkedHashMap<String, ClassType> s) {
        symTable = s;
    }

    public int getIndexOfClass(ClassType s) {
        ClassType[] table = (ClassType[]) symTable.values().toArray();
        for (int i = 0; i < symTable.size(); i++) {
            ClassType cl = table[i];
            if (s == cl)
                return i;
        }
        return -1;
    }

    public boolean dfs(String[] table, int[] pre, int[] it, int iteration, int curr) {
        DeclaredClass cl = (DeclaredClass) symTable.get(table[curr]);
        it[curr] = iteration;
        if (cl.superclass == null)
            return false;
        DeclaredClass sup = cl.superclass;
        int superClIdx = getIndexOfClass(sup);
        pre[superClIdx] = curr;
        if (it[superClIdx] == iteration) {
            return true;
        }
        return dfs(table, pre, it, iteration, superClIdx);
    }

    public void findInheritanceCycles() {
        int n = symTable.size();
        String table[] = (String[]) symTable.keySet().toArray();
        int predecessor[] = new int[n];
        int iteration[] = new int[n];
        for (int i = 1; i < n; i++) {
            if (iteration[i] > 0)
                continue;
            if (dfs(table, predecessor, iteration, i, i)) {
                System.err.format("Cycle in the Inheritance Graph.\n");
            }
        }
    }

    @Override
    public void visit(Program n) {
        for (int i = 0; i < n.cl.size(); i++) {
            n.cl.get(i).accept(this);
        }
        findInheritanceCycles();
    }

    @Override
    public void visit(MainClass n) {
    }

    @Override
    public void visit(ClassDeclSimple n) {
    }

    @Override
    public void visit(ClassDeclExtends n) {
        ClassType superClass = symTable.get(n.j.toString());
        if (superClass instanceof MainClassType) {
            System.err.format("Line %d: Cannot extend the Main class.\n", n.line_number);
            // throw new Exception("Main Class extended.");
        }
        DeclaredClass cl = new DeclaredClass((DeclaredClass) superClass);
        currentClass = cl;
        if (symTable.containsKey(n.i.toString())) {
            System.err.format("Line %d: Classes must have unique names.\n", n.line_number);
        } else {
            symTable.put(n.i.toString(), cl);
        }
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
