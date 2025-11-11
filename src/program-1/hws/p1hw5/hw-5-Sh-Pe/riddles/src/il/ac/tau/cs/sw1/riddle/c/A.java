package il.ac.tau.cs.sw1.riddle.c;

public class A {
	
	private static int k = 1;
	private int l;
	
	public A(int l) {
		this.l=l;
	}
	
	public int getK() {
		return k;
	}
	
	public int getL() {
		return l;
	}

	public static int getFirst() {
		System.out.println("001");
		return 1;
	}
	
	private static int getFirst(int i) {
		System.out.println("002");
		return -10;
	}

	public static double getSecond() {
		System.out.println("003");
		return 1;
	}
	
	public int getSecond(int i) {
		System.out.println("004");
		return l+i;
	}

	public int getThird(int l) {
		System.out.println("005");
		return l;
	}

	public static int getThird() {
		System.out.println("006");
		return k;
	}
	
	public int getForth() {
		System.out.println("007");
		return getForth(0);
	}
	
	public int getForth(int i) {
		System.out.println("008");
		return l+i;
	}

}
