package AST.Visitor;

import AST.*;
import Semantics.*;

public class ResolveClasses {
    SymbolTable symTable;

    public ResolveClasses(SymbolTable s) {
        // Not a Visitor, so don't set Top.symTable
        symTable = s;
    }

    public void resolve(Program n) {
        hoistClass(n.m);
        for (int i = 0; i < n.cl.size(); i++) {
            hoistClass(n.cl.get(i));
        }
        for (int i = 0; i < n.cl.size(); i++) {
            resolveSuperclass(n.cl.get(i));
        }
        findInheritanceCycles();
    }

    private int getIndexOfClass(ClassType s) {
        int n = symTable.size();
        ClassType[] table = symTable.classes().values().toArray(new ClassType[n]);
        for (int i = 0; i < n; i++) {
            ClassType cl = table[i];
            if (s == cl)
                return i;
        }
        return -1;
    }

    /* Errors if a cycle was found. */
    private void dfs(String[] table, int[] pre, int[] it, int iteration, int curr) {
        if (!(symTable.get(table[curr]) instanceof DeclaredClass cl)) return;

        it[curr] = iteration;
        if (!(cl.superclass() instanceof DeclaredClass sup)) return;
        int superClIdx = getIndexOfClass(sup);

        pre[superClIdx] = curr;
        if (it[superClIdx] == iteration) {
            symTable.err();
            StringBuilder err = new StringBuilder("Cycle found in inheritance graph.\n")
                    .append(table[superClIdx])
                    .append(" :⪈ ")
                    .append(table[curr]);

            int next = pre[curr];
            while (next != superClIdx) {
                err.append(" :⪈ ").append(table[next]);
                next = pre[next];
            }
            err.append(" :⪈ ").append(table[superClIdx]);
            throw new RuntimeException(err.toString());
        }
        dfs(table, pre, it, iteration, superClIdx);
    }

    private void findInheritanceCycles() {
        int n = symTable.size();
        String[] table = symTable.classes().keySet().toArray(new String[n]);
        int[] predecessor = new int[n],
                iteration = new int[n];

        for (int i = 1; i < n; i++) {
            if (iteration[i] > 0) continue;
            dfs(table, predecessor, iteration, i, i);
        }
    }

    private void hoistClass(ClassDecl n) {
        if (!symTable.add(n.i, new DeclaredClass(n.i))) {
            symTable.err("Classes must have unique names.", n);
            throw new RuntimeException("Duplicate class declaration.");
        }
    }

    private void hoistClass(MainClass n) {
        MainClassType.setName(n.i1);
        symTable.add(n.i1, MainClassType.get());
    }

    private void resolveSuperclass(ClassDecl c) {
        if (!(c instanceof ClassDeclExtends n)) return;

        ClassType cls = symTable.get(n.i);
        cls.setSuperclass(n.j);
        if (cls.superclass() == Bottom.get()) {
            symTable.err(String.format("Superclass %s cannot be resolved.", n.j), n);
            symTable.add(n.j, Bottom.get());
        }
        if (cls.superclass() == MainClassType.get()) {
            symTable.err("Cannot extend the main class.", n);
        }
    }
}
