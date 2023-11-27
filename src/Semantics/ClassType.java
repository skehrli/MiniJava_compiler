package Semantics;

import java.util.HashMap;
import java.util.Map;

import AST.Identifier;

public interface ClassType extends Type {
    default boolean addMethod(String s, MethodType m) {
        return false;
    }

    default MethodType getMethod(String s) {
        return Bottom.get();
    }

    default boolean addField(String s, InstanceType m) {
        return false;
    }

    default InstanceType getField(String s) {
        return Bottom.get();
    }

    default boolean addMethod(Identifier s, MethodType m) {
        return this.addMethod(s.toString(), m);
    }

    default MethodType getMethod(Identifier s) {
        return this.getMethod(s.toString());
    }

    default boolean addField(Identifier s, InstanceType m) {
        return this.addField(s.toString(), m);
    }

    default InstanceType getField(Identifier s) {
        return this.getField(s.toString());
    }

    default boolean setSuperclass(String s) { return false; }
    default boolean setSuperclass(Identifier i) { return this.setSuperclass(i.toString()); }
    default ClassType superclass() {
        return Top.get();
    }

    default IndexedMap<DeclaredClass> vtable() {
        return new IndexedMap<>();
    }

    default Map<String, Integer> fields() {
        return new HashMap<>();
    }
}
