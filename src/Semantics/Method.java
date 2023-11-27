package Semantics;

import AST.Identifier;

import java.util.HashSet;
import java.util.Map;
import java.util.LinkedHashMap;

public class Method implements MethodType, ScopedType {
    private final InstanceType returnType;
    public final Map<String, InstanceType> parameters = new IndexedMap<>(),
                                           variables = new IndexedMap<>();
    public final HashSet<String> unrecognized = new HashSet<>();
    public String name;

    public Method(String name, InstanceType returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public Method(Identifier name, InstanceType returnType) {
        this(name.s, returnType);
    }

    @Override
    public boolean addVariable(String s, InstanceType i) {
        if (parameters.get(s) != null) return false;
        if (variables.get(s) != null) return false;
        variables.put(s, i);
        return true;
    }

    @Override
    public InstanceType getVariable(String s) {
        if (variables.get(s) == null) return Bottom.get();
        return variables.get(s);
    }

    @Override
    public boolean addParam(String s, InstanceType i) {
        if (parameters.get(s) != null)
            return false;
        parameters.put(s, i);
        return true;
    }

    @Override
    public InstanceType getParam(String s) {
        InstanceType result = parameters.get(s);
        if (Type.valid(result))
            return result;
        return Bottom.get();
    }

    @Override
    public InstanceType getReturn() {
        return returnType;
    }

    @Override
    public int params() { return parameters.size(); }

    @Override
    public boolean seenUnrecognized(String s) {
        return unrecognized.contains(s);
    }

    @Override
    public void addUnrecognized(String s) {
        unrecognized.add(s);
    }
    public String name() { return name; }
}
