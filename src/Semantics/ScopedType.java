package Semantics;

public interface ScopedType extends Type {
    default void addUnrecognized(String s) {}
    default boolean seenUnrecognized(String s) {
        return false;
    }
    String name();
}
