package Semantics;

import java.util.Dictionary;

public class Method implements MethodType {
    private final InstanceType returnType;
    private Dictionary<String, InstanceType> parameterTypes;

    public Method(InstanceType returnType) {
        this.returnType = returnType;
    }

    public boolean addParam(String s, InstanceType i) {
        if (parameterTypes.get(s) != null) return false;
        parameterTypes.put(s, i);
        return true;
    }
}
