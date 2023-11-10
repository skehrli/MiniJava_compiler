package Semantics;

public class Ref implements InstanceType {
    DeclaredClass c;

    public Ref(DeclaredClass c) {
        this.c = c;
    }

    /* a: this := t: b */
    boolean assignmentCompatible(Type b) {
        return Type.subtype(c, b);
    }
}
