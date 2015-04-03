public class TestOverLoadnOverride {

	public static void main(String[] args) {
		A c1 = new C();
		C c2 = new C();
		c1.m1(c2);
	}

}

class A {

	void m1(A a) {
		System.out.print("A");
	}
}

class B extends A {
	void m1(B b) {
		System.out.print("B");
	}
}

class C extends B {
	void m1(C c) {
		System.out.print("C");
	}
}
