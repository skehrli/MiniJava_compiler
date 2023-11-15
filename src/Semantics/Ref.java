package Semantics;

public class Ref implements InstanceType {
    public String s;

    public Ref(String s) {
        this.s = s;
    }

    public String toString() {
        return s;
    }
}
