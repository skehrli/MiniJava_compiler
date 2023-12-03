class Inheritance {
    public static void main(String[] args) {
        System.out.println(new Test().start());
    }
}

class Test {
    public int start() {
        A a1;
        A a2;
        A a3;
        A a4;

        B1 b11;
        B1 b12;

        B2 b2;

        C c;
        
        a1 = new A().start();
        a2 = new B1().start();
        a3 = new B2().start();
        a4 = new C().start();

        b11 = new B1().start();
        b12 = new C().start();

        b2 = new B2().start();

        c = new C().start();

        System.out.println(a1.a());
        System.out.println(a1.b());
        System.out.println(a1.c());
        System.out.println(a1.d());

        System.out.println(a2.a());
        System.out.println(a2.b());
        System.out.println(a2.c());
        System.out.println(a2.d());

        System.out.println(a3.a());
        System.out.println(a3.b());
        System.out.println(a3.c());
        System.out.println(a3.d());

        System.out.println(a4.a());
        System.out.println(a4.b());
        System.out.println(a4.c());
        System.out.println(a4.d());

        System.out.println(b11.a());
        System.out.println(b11.b());
        System.out.println(b11.c());
        System.out.println(b11.d());
        System.out.println(b11.e());

        System.out.println(b12.a());
        System.out.println(b12.b());
        System.out.println(b12.c());
        System.out.println(b12.d());
        System.out.println(b12.e());

        System.out.println(b2.a());
        System.out.println(b2.b());
        System.out.println(b2.c());
        System.out.println(b2.d());

        System.out.println(c.a());
        System.out.println(c.b());
        System.out.println(c.c());
        System.out.println(c.d());
        System.out.println(c.e());

        return 42;
    }
}

class A {
    int a;

    public A start() {
        a = 1;
        return this;
    }

    public int a() {
        return a;
    }

    public int b() {
        return a;
    }

    public int c() {
        return a;
    }

    public int d() {
        return a;
    }
}

class B1 extends A {
    public B1 start() {
        a = 2;
        return this;
    }
    
    public int b() {
        return a;
    }

    public int d() {
        return a;
    }

    public int e() {
        return a;
    }
}

class B2 extends A {
    int a;
    public B2 start() {
        a = 3;
        return this;
    }

    public int b() {
        return a;
    }

    public int d() {
        return a;
    }
}

class C extends B1 {
    public C start() {
        a = 4;
        return this;
    }
    
    public int c() {
        return a;
    }

    public int d() {
        return a;
    }
}
