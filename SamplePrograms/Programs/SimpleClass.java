class SimpleClass {
    public static void main(String[] args) {
        System.out.println(42);
    }
}

class A {
    public int one() {
        return 1;
    }

    public int two() {
        return 2;
    }
}

class B extends A {
    public int two() {
        return 3;
    }

    public int three() {
        return 3;
    }
}