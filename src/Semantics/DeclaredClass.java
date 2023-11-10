package Semantics;

import java.util.Dictionary;
import java.util.Hashtable;

public class DeclaredClass implements ClassType {
    private final Dictionary<String, InstanceType> instances = new Hashtable<>();
    private final Dictionary<String, MethodType> methods = new Hashtable<>();
    private ClassType superclass = Bottom.get();

    // Has superclass = null
    public DeclaredClass() {}

    // Derived class constructor
    public DeclaredClass(ClassType c) {
        superclass = c;
    }

    public boolean addMethod(String s, MethodType m) {
        if (Type.valid(methods.get(s))) return false;
        methods.put(s, m);
        return true;
    }

    public MethodType getMethod(String s) {
        MethodType result = methods.get(s);
        if (!Type.valid(result)) {
            return superclass.getMethod(s);
        }
        return result;
    }

    public boolean addField(String s, InstanceType i) {
        if (Type.valid(instances.get(s))) return false;
        instances.put(s, i);
        return true;
    }

    public InstanceType getField(String s) {
        InstanceType result = instances.get(s);
        if (!Type.valid(result)) {
            return superclass.getField(s);
        }
        return result;
    }
}
