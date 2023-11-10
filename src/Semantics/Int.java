package Semantics;

public class Int implements InstanceType {
    private static Int single;

    public static Int get() {
        if (single == null) single = new Int();
        return single;
    }

    private Int() {}
}
