package Semantics;

public class Ref implements InstanceType {
    public ClassType c;

    public Ref(ClassType c) {
        this.c = c;
    }

    public String toString() {
        return c.toString();
    }
}
