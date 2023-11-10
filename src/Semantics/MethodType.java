package Semantics;

public interface MethodType extends Type {
    default boolean addParam(String s, InstanceType i) {
        return false;
    }

    default InstanceType getParam(String s) {
        return Bottom.get();
    }

    default InstanceType getReturn() {
        return Bottom.get();
    }
}

