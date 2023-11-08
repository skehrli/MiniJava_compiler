package Semantics;

import java.util.Dictionary;
import java.util.Hashtable;

public class Type {
    public static boolean assignmentCompatible(Type a, Type b) {
        throw new RuntimeException();
    }

    public static boolean subtype(Type a, Type b) {
        throw new RuntimeException();
    }
}

abstract class InstanceType extends Type {}

class Bottom extends InstanceType {
    static Bottom single;
    static Bottom get() {
        if (single == null) single = new Bottom();
        return single;
    }

    private Bottom() {}
}

class Int extends InstanceType {
    static Int single;
    static Int get() {
        if (single == null) single = new Int();
        return single;
    }

    private Int() {}
}

class Boolean extends InstanceType {
    static Boolean single;
    static Boolean get() {
        if (single == null) single = new Boolean();
        return single;
    }

    private Boolean() {}
}

class Array extends InstanceType {
    static Array single;
    static Array get() {
        if (single == null) single = new Array();
        return single;
    }

    private Array() {}
}

class Ref extends InstanceType {
    ClassType c;
    private Ref(ClassType c) {
        this.c = c;
    }
}

class ClassType extends Type {
    Dictionary<String, InstanceType> instances = new Hashtable<>();
    Dictionary<String, MethodType> methods = new Hashtable<>();
    ClassType superclass;

    // Has superclass = null
    public ClassType() {}
    // Derived class constructor
    public ClassType(ClassType c) {superclass = c;}
    boolean addMethod(String s, MethodType m) {
        if (methods.get(s) != null) return false;
        methods.put(s, m);
        return true;
    }

    boolean addInstance(String s, InstanceType i) {
        if (instances.get(s) != null) return false;
        instances.put(s, i);
        return true;
    }
}

class MethodType extends Type {
    InstanceType returnType;
    Dictionary<String, InstanceType> parameterTypes;

    public MethodType(InstanceType returnType) {
        this.returnType = returnType;
    }

    boolean addParam(String s, InstanceType i) {
        if (parameterTypes.get(s) != null) return false;
        parameterTypes.put(s, i);
        return true;
    }
}
