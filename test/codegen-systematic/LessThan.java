class LessThan {
    public static void main(String[] args) {
        System.out.println(new Test().start());
    }
}

class Test {
    public int start() {
        System.out.println(this.correct());
        System.out.println(this.incorrect());
        return 42;
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
