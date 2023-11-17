package Semantics;

public class MainMethod implements MethodType, ScopedType {
    private static final MainMethod single = new MainMethod();

    public static MainMethod get() {
        return single;
    }

    private MainMethod() {}

    public String name() { return "main"; }
}
