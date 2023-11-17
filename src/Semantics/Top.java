package Semantics;

public class Top implements ClassType, InstanceType, MethodType {
    private static Top single;
    public static SymbolTable symTable;

    public static Top get() {
        if (single == null)
            single = new Top();
        return single;
    }

    private Top() {}

    public String toString() {
        return "⊤";
    }
}
