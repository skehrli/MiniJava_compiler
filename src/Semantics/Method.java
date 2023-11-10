package Semantics;

import java.util.Map;
import java.util.LinkedHashMap;

public class Method implements MethodType {
    private InstanceType returnType = Bottom.get();
    private final Map<String, InstanceType> parameterTypes = new LinkedHashMap<>();

    public Method() {}
    public Method(InstanceType returnType) {
        this.returnType = returnType;
    }

    @Override
    public boolean addParam(String s, InstanceType i) {
        if (parameterTypes.get(s) != null) return false;
        parameterTypes.put(s, i);
        return true;
    }

    @Override
    public InstanceType getParam(String s) {
        InstanceType result = parameterTypes.get(s);
        if (Type.valid(result)) return result;
        return Bottom.get();
    }

    @Override
    public InstanceType getReturn() {
        return returnType;
    }
}
