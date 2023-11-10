package Semantics;

import java.util.Dictionary;
import java.util.Hashtable;

public interface ClassType extends Type {
    boolean addMethod(String s, MethodType m);

    MethodType getMethod(String s);
    boolean addField(String s, MethodType m);
    InstanceType getField(String s);
}

