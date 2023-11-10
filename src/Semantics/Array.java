package Semantics;

public class Array implements InstanceType {
    private static final Array single = new Array();

    public static Array get() {
        return single;
    }

    private Array() {
    }
}
