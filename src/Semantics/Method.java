package Semantics;

import java.util.Map;
import java.util.LinkedHashMap;

public class Method implements MethodType {
    private InstanceType returnType = Bottom.get();
    public final Map<String, InstanceType> parameterTypes = new LinkedHashMap<>();
    public final Map<String, InstanceType> variables = new LinkedHashMap<>();
    // private static final MethodType single = new Method();

    // public static Method get() {
    // return single;
    // }

    public Method() {
    }

    public Method(InstanceType returnType) {
        this.returnType = returnType;
    }

    public boolean addVariable(String s, InstanceType i) {
        if (variables.get(s) != null)
            return false;
        variables.put(s, i);
        return true;
    }

    @Override
    public boolean addParam(String s, InstanceType i) {
        if (parameterTypes.get(s) != null)
            return false;
        parameterTypes.put(s, i);
        return true;
    }

    @Override
    public InstanceType getParam(String s) {
        InstanceType result = parameterTypes.get(s);
        if (Type.valid(result))
            return result;
        return Bottom.get();
    }

    @Override
    public InstanceType getReturn() {
        return returnType;
    }
}
