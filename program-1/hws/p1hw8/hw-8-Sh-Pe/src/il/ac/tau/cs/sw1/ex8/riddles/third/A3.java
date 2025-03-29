package il.ac.tau.cs.sw1.ex8.riddles.third;

public class A3 extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String s;
	public A3(String s){
		this.s = s;
	}
	
	public void foo(String s) throws Exception{
		if (s.equals(this.s)){
			throw new Exception();
		}
	}

}
