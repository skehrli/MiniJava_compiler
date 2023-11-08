package Semantics;

import java.util.Dictionary;
import java.util.Hashtable;

public class SymbolTable {
    SymbolTable parentScope;
    Dictionary<String, MethodType> methods = new Hashtable<>();
    Dictionary<String, InstanceType> instances = new Hashtable<>();
    Dictionary<String, ClassType> classes = new Hashtable<>();
    Dictionary<String, SymbolTable> children = new Hashtable<>();

    public SymbolTable() {}
    public SymbolTable(SymbolTable s) {parentScope = s;}

    public MethodType getMethod(String s) {
        MethodType t = methods.get(s);
        if (t == null && parentScope == null) return null;
        else if (t == null) return parentScope.getMethod(s);
        return t;
    }

    public boolean addMethod(String s, MethodType t) {
        if (methods.get(s) != null) return false;
        methods.put(s, t);
        children.put(s, new SymbolTable(this));
        return true;
    }

    public InstanceType getInstance(String s) {
        InstanceType t = instances.get(s);
        if (t == null && parentScope == null) return null;
        else if (t == null) return parentScope.getInstance(s);
        return t;
    }

    public boolean addInstance(String s, InstanceType t) {
        if (instances.get(s) != null) return false;
        instances.put(s, t);
        return true;
    }

    public ClassType getClass(String s) {
        ClassType t = classes.get(s);
        if (t == null && parentScope == null) return null;
        else if (t == null) return parentScope.getClass(s);
        return t;
    }

    public boolean addClass(String s, ClassType t) {
        if (classes.get(s) != null) return false;
        classes.put(s, t);
        children.put(s, new SymbolTable(this));
        return true;
    }

    public SymbolTable getScope(String s) {
        return children.get(s);
    }
}
