package il.ac.tau.cs.sw1.riddle.b;

/**
 * Do not change this code!
 */
public class B {

	public static void foo(B b) {
		b.methodD();
	}
	
	private String s1;
	private String s2;
	private String s3;

	public B(String s1, String s2, String s3) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
	}
	
	public B(B other, String s) {
		other.methodC(s);
	}
	
	public void methodA() {
		System.out.println(this.s1);
		System.out.println(this.s2);
	}
	
	public void methodB(B b) {
		System.out.println(this.s3);
	}

	private void methodC(String string) {
		System.out.println(this.s1);
	}


	private void methodD() {
		System.out.println(s2);
	}
	
	private void methodE() {
		System.out.println(s3);
	}

}
