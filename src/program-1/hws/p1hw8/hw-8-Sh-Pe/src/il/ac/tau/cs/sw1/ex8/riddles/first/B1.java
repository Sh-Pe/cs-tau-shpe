package il.ac.tau.cs.sw1.ex8.riddles.first;

public class B1 extends A1 {

	@Override
	protected String foo() {
		C1 c = new C1();
		String cOutput = c.foo();
		int n = cOutput.length();
		return cOutput.substring(0, n - 1);
	}

}
