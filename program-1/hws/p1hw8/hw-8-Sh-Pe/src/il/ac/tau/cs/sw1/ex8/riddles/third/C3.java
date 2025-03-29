package il.ac.tau.cs.sw1.ex8.riddles.third;



public class C3 {
	public static void main(String[] args) {
		A3 a = new B3(args[0]);
		try{
			a.foo(args[0]);
		}
		catch(B3 b){
			if (b.getMessage().equals(args[0])){
				System.out.println("success!");
			}
		}
		catch(Exception exp){
			return;
		}
	}
}
