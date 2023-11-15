package Semantics;

import AST.ASTNode;
import AST.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

public class SymbolTable {
    public Map<String, ClassType> classes = new LinkedHashMap<>();
    public boolean error = false;
    public boolean add(String s, ClassType t) {
        if (classes.containsKey(s)) return false;
        classes.put(s, t);
        return true;
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
}
