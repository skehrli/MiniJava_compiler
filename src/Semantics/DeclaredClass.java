package Semantics;

import AST.Identifier;

import java.util.Map;
import java.util.LinkedHashMap;

public class DeclaredClass implements ClassType {
    public final Map<String, InstanceType> instances = new LinkedHashMap<>();
    public final Map<String, MethodType> methods = new LinkedHashMap<>();
    public String superclass;
    public String name;
    // public ClassType superclass = Bottom.get();

    // Has superclass = null
    public DeclaredClass(String name) {
        this.name = name;
    }

    public DeclaredClass(Identifier name) {
        this.name = name.toString();
    }

    // Derived class constructor
    public DeclaredClass(String name, String superclass) {
        this.name = name;
        this.superclass = superclass;
    }

    public DeclaredClass(String name, Identifier superclass) {
        this(name, superclass.toString());
    }

    public DeclaredClass(Identifier name, String superclass) {
        this(name.toString(), superclass);
    }

    public DeclaredClass(Identifier name, Identifier superclass) {
        this(name.toString(), superclass.toString());
    }

    public boolean addMethod(String s, MethodType m) {
        if (Type.valid(methods.get(s)))
            return false;
        methods.put(s, m);
        return true;
    }

    public MethodType getMethod(String s) {
        MethodType result = methods.get(s);
        if (!Type.valid(result)) {
            if (superclass != null)
                return superclass().getMethod(s);
        }
        return result;
    }

    public boolean addField(String s, InstanceType i) {
        if (Type.valid(instances.get(s)))
            return false;
        instances.put(s, i);
        return true;
    }

    public InstanceType getField(String s) {
        InstanceType result = instances.get(s);
        if (!Type.valid(result)) {
            if (superclass != null)
                return superclass().getField(s);
            else
                return null;
        }
        return result;
    }

    public String toString() { return this.name; }

    @Override
    public boolean setSuperclass(String s) {
        superclass = s;
        return true;
    }

    @Override
    public ClassType superclass() {
        if (superclass == null) return Top.get();
        return Top.symTable.get(superclass);
    }
}
