class Arrays {
    public static void main(String[] args) {
        System.out.println(new ArraysT().start());
    }
}

class ArraysT {
    public int start() {
        int[] a;
        int i;
        a = new int[5];
        i = 0;
        while (i < a.length) {
            a[5 - i - 1] = i;
            i = i + 1;
        }
        i = 0;
        while (i < a.length) {
            System.out.println(i);
            i = i + 1;
        }
        i = 0;
        while (i < a.length) {
            a[i] = i;
            i = i + 1;
        }
        i = 0;
        while (i < a.length) {
            System.out.println(i);
            i = i + 1;
        }
        return 42;
    }
}
