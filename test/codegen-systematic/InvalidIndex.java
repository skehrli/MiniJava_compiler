class Main {
    public static void main(String[] args) {
        System.out.println(new C().start());
    }
}

class C {
    public int start() {
        int[] arr;
        arr = new int[3];
        arr[0] = 0;
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;
        arr[4] = 4;
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);
        System.out.println(arr[4]);
        return 42;
    }
}