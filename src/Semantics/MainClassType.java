package Semantics;

import jflex.Main;

public class MainClassType implements ClassType{
    MainMethod main = MainMethod.get();

    private static final MainClassType single = new MainClassType();

    public static MainClassType get() {
        return single;
    }

    @Override
    public MethodType getMethod(String s) {
        return main;
    }
}
