package Semantics;

public class Boolean implements InstanceType {
    private static Boolean single;

    public static Boolean get() {
        if (single == null) single = new Boolean();
        return single;
    }

    private Boolean() {}
}
