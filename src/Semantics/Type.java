package Semantics;

public interface Type {
    /* t1: a := t2: b */
    static boolean assignmentCompatible(Type a, Type b) {
        // t1: a := t2: b exactly when b <: a
        return subtype(b, a);
    }

    /* a <: b */
    static boolean subtype(Type a, Type b) {
        return a == b;
    }
}
