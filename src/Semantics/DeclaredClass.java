package Semantics;

import java.util.Map;
import java.util.LinkedHashMap;

public class DeclaredClass implements ClassType {
    public final Map<String, InstanceType> instances = new LinkedHashMap<>();
    public final Map<String, MethodType> methods = new LinkedHashMap<>();
    public DeclaredClass superclass = null;
    public String name;
    // public ClassType superclass = Bottom.get();

    // Has superclass = null
    public DeclaredClass(String name) {
        this.name = name;
    }

    // Derived class constructor
    public DeclaredClass(String name, DeclaredClass c) {
        this.name = name;
        superclass = c;
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
                return superclass.getMethod(s);
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
                return superclass.getField(s);
            else
                return null;
        }
        return result;
    }

    public String toString() { return this.name; }
}
