class A {
	public static void main(String[] a) {
		System.out.println(new C().method(1, 2, 3));
	}
}

class B {
  Boolean s;
  public C method(int a, int b, int c) {
		System.out.println(new C().method(1, 2, 3));
    return new C();
  }
}
 
class C extends B {
  public C method(int a, int b, int c) {
    return new C();
  }
}

