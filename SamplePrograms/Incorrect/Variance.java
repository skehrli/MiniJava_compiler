class Variance {
    public static void main(String[] args) {
        System.out.println(1);
    }
}

class A {}

class B extends A {}

class C extends B {}

class M {
    public B method(B b) {
        return new B();
    }
}

class N extends M {
    public C method(B b) {return new C();}
}

class O extends M {
    public A method(B b) {return new A();}
}

class P extends M {
    public B method(A b) {return new B();}
}

class Q extends M {
    public B method(C b) {return new B();}
}
