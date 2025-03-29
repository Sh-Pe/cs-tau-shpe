

public class Assignment01Q02 {

	public static void main(String[] args) {
		// do not change this part below
		double piEstimation = 0.0;
		
		// *** your code goes here below ***
		
		int iterationCount = Integer.parseInt(args[0]);
		for (int i = 0; i < iterationCount; i++) {
			piEstimation += 4.0 / (2*i + 1) * (Math.pow(-1, i));
		}
		
		// do not change this part below
		System.out.println(piEstimation + " " + Math.PI);

	}

}
