package Semantics;

public class Array implements InstanceType {
    private static Array single;

    public static Array get() {
        if (single == null) single = new Array();
        return single;
    }

    private Array() {}
}
