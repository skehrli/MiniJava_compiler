package Semantics;

public class Bottom extends InstanceType {
    private static Bottom single;

    public static Bottom get() {
        if (single == null) single = new Bottom();
        return single;
    }

    private Bottom() {}
}
