class A {
	public int v;
	public static int a = 1;
	public final boolean FINAL = true;
	public static final int STATIC_FINAL = 2;
	
	public A() {
		this.v = -1;
	}
	
	public A(int _v) {
		this.v = _v;
	}
	
	public final void get() {
		print 1;
	}
	
	public static void afficher() {
		print 2;
	}
	
	public static final void test() {
		print 3;
	}
	
	// public abstract void test2();
}

public class Main {

	public static void main(String args[]) {
		A a1 = new A();
        A a2 = new A( 5 );
        // A.afficher(); marche pas
        //print A.a;
	}
}