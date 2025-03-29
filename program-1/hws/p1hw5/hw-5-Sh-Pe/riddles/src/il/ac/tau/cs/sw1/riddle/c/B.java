package il.ac.tau.cs.sw1.riddle.c;

public class B {

	public static void main(String[] args) {
		int j = 0;
		j += A.getFirst();
		j += A.getSecond();
		j += A.getThird();
		A a = new A(3);
		j += a.getForth();
		if (j >= 4) {
			System.out.println(j);
			System.out.println("success!");
		}
	}
}
