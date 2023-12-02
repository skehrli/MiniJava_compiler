// In MiniJava, null gets treated as an identifier rather than a value, which is what's supposed to happen
class Main {
    public static void main(String[] args) {
        System.out.println(1);
    }
}

class T {
    T n;
    public int start() {
        n = a;
        n = null;
        return a + b;
    }
}