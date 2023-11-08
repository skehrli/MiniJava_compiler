package Semantics;

public class Int extends InstanceType {
    private static Int single;

    public static Int get() {
        if (single == null) single = new Int();
        return single;
    }

    private Int() {}
}
