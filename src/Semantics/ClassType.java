package Semantics;

import java.util.Dictionary;
import java.util.Hashtable;

public interface ClassType extends Type {
    default boolean addMethod(String s, MethodType m) {
        return false;
    }

    default MethodType getMethod(String s) {
        return Bottom.get();
    }

    default boolean addField(String s, MethodType m) {
        return  false;
    }

    default InstanceType getField(String s) {
        return Bottom.get();
    }
}

