package il.ac.tau.cs.sw1.riddle.b;

/**
 * Do not change this code!
 */
public class C {

	public static void main(String[] args) {
		B b = new B(args[0], args[1], args[2]);
		A.printA(b);
		System.out.println("***");
		A a = new A(b);
		a.printA2();
		System.out.println("***");
		A.printA3(a);
	}

}
