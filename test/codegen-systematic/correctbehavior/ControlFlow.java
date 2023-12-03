class ControlFlow {
    public static void main(String[] args) {
        System.out.println(new ControlFlowT().start());
    }
}

class ControlFlowT {
    public int start() {
        System.out.println(this.ifm());
        System.out.println(this.block());
        System.out.println(this.whilem());
        System.out.println(this.recurse());
        System.out.println(this.methods());
        return 42;
    }

    public int ifm() {
        if (true) {
            System.out.println(0);
        } else {}
        if (false) {
            System.out.println(1);
        } else {}
        if (true) {}
        else {System.out.println(2);}
        if (false) {}
        else {System.out.println(3);}
        if (true) {System.out.println(4);}
        else {System.out.println(5);}
        if (false) {System.out.println(6);}
        else {System.out.println(7);}
        return 21;
    }

    public int block() {
        int a;
        {
    
        }
        {
            System.out.println(1);
        }
        {
            System.out.println(2);
            System.out.println(3);
            a = 4;
        }
        {
            System.out.println(a);
        }
        System.out.println(a);
        return 21;
    }

    public int whilem() {
        int i;
        i = 10;
        while (0 < i) {
            System.out.println(i);
            i = i - 2;
        }
        while (i < 10) {
            System.out.println(i);
            i = i + 1;
        }
        return 21;
    }

    public int recurse() {
        int i;
        int ntb;
        int[] arr;
        arr = new int[5];
        i = 0;
        while (i < arr.length) {
            arr[i] = i;
            System.out.println(arr[i]);
            i = i + 1;
        }
        System.out.println(this.square(arr, 0));
        while (i < arr.length) {
            System.out.println(arr[i]);
            i = i + 1;
        }
        return 21;
    }

    public int square(int[] arr, int i) {
        int result;
        if (i - 1 < arr.length && arr.length < i + 1) {
            result = 55;
        } else {
            arr[i] = arr[i] * arr[i];
            System.out.println(this.square(arr, i + 1));
        }
        result = 33;
        return result;
    }

    public int methods() {
        boolean ntb;
        ntb = this.one(true);
        ntb = this.two(true, true);
        ntb = this.three(true, true, true);
        ntb = this.four(true, true, true, true);
        ntb = this.five(true, true, true, true, true);
        return 21;
    }

    public boolean one(boolean a) {
        return a;
    }

    public boolean two(boolean a, boolean b) {
        return a && b;
    }

    public boolean three(boolean a, boolean b, boolean c) {
        return a && b && c;
    }

    public boolean four(boolean a, boolean b, boolean c, boolean d) {
        return a && b && c && d;
    }

    public boolean five(boolean a, boolean b, boolean c, boolean d, boolean e) {
        return a && b && c && d && e;
    }
}
