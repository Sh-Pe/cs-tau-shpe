import java.util.Arrays;

public class Assignment01Q04 {

	public static void main(String[] args) {
		int[] primes = new int[20];	
		int primeAmount = 1;
		int numToCheck = 3; 
		primes[0] = 2;
		while (primeAmount < primes.length) {
			boolean isPrime = true;
			double sqrt = Math.sqrt(numToCheck);
			int top = (int) sqrt + 1;
			for (int i = 0; i < primeAmount && primes[i] < top; i++) {
				if (numToCheck % primes[i] == 0) {
					isPrime = false;
					break;
				}
			}
			if (isPrime) {
				primes[primeAmount] = numToCheck;
				primeAmount++;
			}
			numToCheck += 2;
		}
		System.out.println(Arrays.toString(primes));


	}

}
