class A {
	
	public void m() {
		print 1;
	}
}

class B extends A {

	public void m() {
		print 2;
	}
	
}

public class Main {

	public static void main(String args[]) {
		A a = new A();
		a.m();
		
		B b = new B();
		b.m();
		
		a = b;
		a.m();
		
		print 1;
	}
}