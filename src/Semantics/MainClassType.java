package Semantics;

public class MainClassType implements ClassType {
    MainMethod main = MainMethod.get();

    private static final MainClassType single = new MainClassType();

    public MainClassType(MainMethod main) {
        this.main = main;
    }

    public static MainClassType get() {
        return single;
    }

    @Override
    public MethodType getMethod(String s) {
        if (s.equals("main"))
            return main;
        return Bottom.get();
    }

    public MethodType getMethod() {
        return main;
    }

    private MainClassType() {
    }
}
