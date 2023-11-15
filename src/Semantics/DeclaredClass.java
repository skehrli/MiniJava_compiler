package Semantics;

import AST.Identifier;

import java.util.Map;
import java.util.LinkedHashMap;

public class DeclaredClass implements ClassType {
    public final Map<String, InstanceType> instances = new LinkedHashMap<>();
    public final Map<String, MethodType> methods = new LinkedHashMap<>();
    private String superclass = null;
    public String name;

    public DeclaredClass(String name) {
        this.name = name;
    }

    public DeclaredClass(Identifier name) {
        this(name.toString());
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
            return superclass().getField(s);
        }
        return result;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public boolean setSuperclass(String s) {
        superclass = s;
        return true;
    }

    @Override
    public ClassType superclass() {
        if (superclass == null)
            return Top.get();
        return Top.symTable.get(superclass);
    }
}
