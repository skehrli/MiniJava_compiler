package Semantics;

import AST.ASTNode;
import AST.Identifier;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
    private final Map<String, ClassType> classes = new IndexedMap<>();
    private boolean error = false;

    public boolean error() { return error; }
    public int size() { return classes.size(); }
    public boolean add(String s, ClassType t) {
        if (classes.containsKey(s)) return false;
        classes.put(s, t);
        return true;
    }

    public Map<String, ClassType> classes() {
        return Collections.unmodifiableMap(classes);
    }

    public boolean add(Identifier s, ClassType t) {
        return this.add(s.toString(), t);
    }

    public ClassType get(String s) {
        if (s == null) return Top.get();
        if (!classes.containsKey(s)) return Bottom.get();
        return classes.get(s);
    }

    public ClassType get(Identifier s) {
        return this.get(s.toString());
    }

    public void err(String s, ASTNode n) {
        System.err.format("Line %d: %s\n", n.line_number, s);
        error = true;
    }

    public void err(String s) {
        System.err.println(s);
        error = true;
    }

    public void err() {
        error = true;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        for (String s : classes.keySet()) {
            ClassType cl = get(s);
            if (cl == MainClassType.get()) {
                b.append("main class ").append(cl).append('\n');
                continue;
            }
            if (cl == Bottom.get()) {
                b.append(String.format("class %s ∷ %s\n", s, cl));
                continue;
            }
            if (!(cl instanceof DeclaredClass cls)) continue;
            b.append("class ")
                    .append(cls)
                    .append(cls.superclass() == Top.get() ? "" : " <: " + cls.superclass)
                    .append('\n');

            for (String decl : cls.instances.keySet()) {
                b.append(String.format("\tfield %s ∷ %s\n", decl, cls.getField(decl)));
            }
            for (String decl : cls.methods.keySet()) {
                b.append("\tmethod ").append(decl).append(" ∷ ");
                if (cls.getMethod(decl) == Bottom.get()) {
                    b.append(Bottom.get()).append('\n');
                    continue;
                }
                if (!(cls.getMethod(decl) instanceof Method method)) continue;
                b.append('(');

                for (Map.Entry<String, InstanceType> entry : method.parameters.entrySet()) {
                    b.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
                }
                if (!method.parameters.isEmpty())
                    b.delete(b.length() - 2, b.length());
                b.append(") ↦ ").append(method.getReturn()).append('\n');
                for (String var : method.variables.keySet()) {
                    b.append(String.format("\t\tvariable %s ∷ %s\n", var, method.getVariable(var)));
                }
            }
        }
        return b.toString();
    }
}
