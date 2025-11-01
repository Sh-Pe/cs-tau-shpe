
public class Assignment01Q03 {

	public static void main(String[] args) {
		int numOfOdd = 0;
		int n = Integer.parseInt(args[0]);
		// *** your code goes here below ***
		
		
		System.out.println("The first "+ n +" Fibonacci numbers are:");
		// *** your code goes here below ***
		
		int prev = 1;
		int current = 1;
		int temp;
		for (int i = 0; i < n; i++) {
			if (prev % 2 == 1) {
				numOfOdd++;
			} if (i == n - 1) {
				System.out.print(prev);
			} else {
				System.out.print(prev + " ");
			}
			
			temp = current;
			current = prev + temp;
			prev = temp;
		}
		System.out.println();
		
		System.out.println("The number of odd numbers is: "+numOfOdd);

	}

}
