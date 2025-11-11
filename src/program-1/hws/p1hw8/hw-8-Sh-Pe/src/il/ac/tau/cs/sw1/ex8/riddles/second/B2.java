package il.ac.tau.cs.sw1.ex8.riddles.second;

public class B2 {

	public A2 getA(int k) {
		return new subA2(k);
	}

	private static class subA2 extends A2 {
		private final int k;

		private subA2(int k) {
			this.k = k;
		}

		@Override
		public String foo(String str) {
			return str.substring(0, (int) str.length() / (k + 1));
		}

	}

}
