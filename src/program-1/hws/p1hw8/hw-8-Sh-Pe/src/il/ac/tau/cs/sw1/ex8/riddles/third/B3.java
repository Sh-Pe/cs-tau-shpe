package il.ac.tau.cs.sw1.ex8.riddles.third;

public class B3 extends A3 {

	private final String msg;

	public B3(String s) {
		super(s);
		this.msg = s;
	}

	@Override
	public void foo(String s) throws B3 {
		try {
			super.foo(s);
		} catch (Exception e) {
			throw new B3(this.s);
		}
	}

	@Override
	public String getMessage() {
		return msg;
	}

}