class Main {
    public static void main(String[] args) {
        System.out.println(1);
    }
}

class A {
    int a;
}

class B extends A {}

class C extends B {}

class D extends C {
    public boolean Init() {
        return a;
    }
}