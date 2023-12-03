class IntegralOperators {
    public static void main(String[] args) {
        System.out.println(new Test().start());
    }
}

class Test {
    public int start() {
        int ntb;
        System.out.println(this.add());
        System.out.println(this.sub());
        System.out.println(this.mul());
        System.out.println(this.all());
        ntb = new LessThan().start();
        System.out.println(ntb);
        return 42;
    }

    public int add() {
        System.out.println(1 + 1);
        System.out.println(1 + 2);
        System.out.println(2 + 1);
        return 21;
    }
    
    public int sub() {
        System.out.println(1 - 1);
        System.out.println(1 - 2);
        System.out.println(2 - 1);
        return 21;
    }

    public int mul() {
        System.out.println(1 * 0);
        System.out.println(0 * 1);
        System.out.println(1 * 1);
        System.out.println(1 * 2);
        System.out.println(2 * 1);
        return 21;
    }

    public int all() {
        System.out.println(1 * (0 - 1));
        System.out.println((0 - 1) * 2);
        System.out.println((0 - 1) + 1);
        return 21;
    }
}

class LessThan {
    public int start() {
        System.out.println(this.correct());
        System.out.println(this.incorrect());
        return 21;
    }

    public int correct() {
        int result;
        if (1 < 2) {
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }

    public int incorrect() {
        int result;
        if (2 < 1) {
            result = 1;
        } else {
            result = 0;
        }
        return result;
    }
}
