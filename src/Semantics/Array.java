package Semantics;

public class Array extends InstanceType {
    private static Array single;

    public static Array get() {
        if (single == null) single = new Array();
        return single;
    }

    private Array() {}
}
