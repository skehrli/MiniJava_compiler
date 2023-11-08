package Semantics;

import java.util.Dictionary;
import java.util.Hashtable;

public class ClassType extends Type {
    Dictionary<String, InstanceType> instances = new Hashtable<>();
    Dictionary<String, MethodType> methods = new Hashtable<>();
    ClassType superclass;

    // Has superclass = null
    public ClassType() {}

    // Derived class constructor
    public ClassType(ClassType c) {
        superclass = c;
    }

    public boolean addMethod(String s, MethodType m) {
        if (methods.get(s) != null) return false;
        methods.put(s, m);
        return true;
    }

    public boolean addInstance(String s, InstanceType i) {
        if (instances.get(s) != null) return false;
        instances.put(s, i);
        return true;
    }
}
