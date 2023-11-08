package Semantics;

import java.util.Dictionary;
import java.util.Hashtable;

public class SymbolTable {
    SymbolTable parentScope;
    Dictionary<String, Type> methods = new Hashtable<>();
    Dictionary<String, Type> instances = new Hashtable<>();

    public Type getMethod(String s) {
        return methods.get(s);
    }

    public Type addMethod(String s, Type t) {
        return methods.put(s, t);
    }

    public Type getInstance(String s) {
        return instances.get(s);
    }

    public Type addInstance(String s, Type t) {
        return instances.put(s, t);
    }
}
