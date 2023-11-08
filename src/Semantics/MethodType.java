package Semantics;

import java.util.Dictionary;

public class MethodType extends Type {
    private final InstanceType returnType;
    private Dictionary<String, InstanceType> parameterTypes;

    public MethodType(InstanceType returnType) {
        this.returnType = returnType;
    }

    public boolean addParam(String s, InstanceType i) {
        if (parameterTypes.get(s) != null) return false;
        parameterTypes.put(s, i);
        return true;
    }
}
