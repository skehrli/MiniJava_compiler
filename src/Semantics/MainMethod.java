package Semantics;

public class MainMethod implements MethodType {
    private static final MainMethod single = new MainMethod();

    public static MainMethod get() {
        return single;
    }

    private MainMethod() {}
}
