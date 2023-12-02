class ShortCircuitAnd {
    public static void main(String[] args) {
        System.out.println(new Test().start());
    }
}

class Test {
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
