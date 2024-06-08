class A {
	private int v;
	
	public A() {
		this.v = -1;
	}
	
	public A(int _v) {
		this.v = _v;
	}
	
}

public class Main {

	public static void main(String args[]) {
		A a1 = new A();
        A a2 = new A( 5 );
        // Erreur d'accès à v
        print a2.v;
    }
}