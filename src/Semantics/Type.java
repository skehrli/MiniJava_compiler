package Semantics;

public interface Type {

    /* t1: a := t2: b */
    static boolean assignmentCompatible(InstanceType a, InstanceType b) {
        if (b == Bottom.get() || a == Top.get()) {
            return true;
        }
        if (!(a instanceof Ref ar) || !(b instanceof Ref br)) {
            return a == b;
        }
        ClassType c1 = Top.symTable.get(ar.s),
                  c2 = Top.symTable.get(br.s);
        while (c1 != c2 && c2.superclass() != Bottom.get() && c2.superclass() != Top.get()) {
            c2 = c2.superclass();
        }
        return c1 == c2;
    }

    static boolean sameType(Semantics.Type a, Semantics.Type b) {
        // either they are the same singleton type or they are a ref
        // of the same class
        if (a == b)
            return true;
        if (!(a instanceof Ref && b instanceof Ref))
            return false;
        return Top.symTable.get(((Ref) a).s) == Top.symTable.get(((Ref) b).s);
    }

    static boolean valid(Type t) {
        return t != Bottom.get() && t != null;
    }
}
