package Semantics;

public class Ref implements InstanceType {
    public ClassType c;

    public Ref(ClassType c) {
        this.c = c;
    }

    /* a: this := t: b */
    boolean assignmentCompatible(Type b) {
        return Type.subtype(c, b);
    }

    public String toString() {
        return c.toString();
    }
}
