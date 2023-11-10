package Semantics;

public class Boolean implements InstanceType {
    private static final Boolean single = new Boolean();

    public static Boolean get() {
        return single;
    }

    private Boolean() {
    }

    public String toString() {
        return "Boolean";
    }
}
