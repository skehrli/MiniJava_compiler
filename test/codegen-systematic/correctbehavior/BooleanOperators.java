class BooleanOperators {
    public static void main(String[] args) {
        System.out.println(new Test().start());
    }
}

class Test {
    public int start() {
        System.out.println(new Not().start());
        System.out.println(new ShortCircuitAnd().start());
        return 21;
    }
}

class Not {
    public int start() {
        ShortCircuitAnd a;
        a = new ShortCircuitAnd();
        System.out.println(a.intofbool(!true));
        System.out.println(a.intofbool(!false));
        System.out.println(a.intofbool(!(a.truem() && a.truem())));
        System.out.println(a.intofbool(!(a.truem() && a.falsem())));
        System.out.println(a.intofbool(!(a.falsem() && a.truem())));
        System.out.println(a.intofbool(!(a.falsem() && a.falsem())));
        return 42;
    }
}

class ShortCircuitAnd {
    public int start() {
        System.out.println(this.intofbool(this.truem() && this.truem()));
        System.out.println(this.intofbool(this.truem() && this.falsem()));
        System.out.println(this.intofbool(this.falsem() && this.truem()));
        System.out.println(this.intofbool(this.falsem() && this.falsem()));
        return 42;
    }

    public boolean truem() {
        System.out.println(5);
        return true;
    }

    public boolean falsem() {
        System.out.println(6);
        return false;
    }

    public int intofbool(boolean b) {
        int result;
        if (b) result = 1;
        else result = 0;
        return result;
    }
}
