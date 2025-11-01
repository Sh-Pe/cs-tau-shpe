package il.ac.tau.cs.sw1.polynomial;

import java.util.Arrays;
import java.util.Objects;

public class Polynomial {

	private double[] coefficients;

	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial() {
		this.coefficients = new double[] {0};
	}
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) {
		if (coefficients.length == 0) {
			coefficients = new double[] {0};
		}
		this.coefficients = coefficients;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial) {
		double[] co1 = this.coefficients;
		double[] co2 = polynomial.coefficients;
		if (co1.length < co2.length) {
			double[] temp = co1;
			co1 = co2;
			co2 = temp;
		}
		double[] outCo = new double[co1.length];
		for (int i = 0; i < co2.length; i++) {
			outCo[i] = co1[i] + co2[i];
		}
		System.arraycopy(co1, co2.length, outCo, co2.length, co2.length - co1.length);
		return new Polynomial(outCo);
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a) {
		return new Polynomial(Arrays.stream(this.coefficients).map((double co) -> co * a).toArray());
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		if (Arrays.equals(this.coefficients, new double[]{0})) {
			return 0;
		} else {
			int deg = 0;
			for (int i = 0; i < this.coefficients.length; i++) {
				if (coefficients[i] != 0) {
					deg = i;
				}
			}
			return deg;
		}
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n) {
		if (coefficients.length - 1 < n) {
			return 0;
		} else {
			return coefficients[n];
		}
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that that the coefficient of the variable x 
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c) {
		if (coefficients.length > n) {
			coefficients[n] = c;
		}
		double[] newCo = new double[n + 1];
		System.arraycopy(coefficients, 0, newCo, 0, coefficients.length);
		newCo[n] = c;
		this.coefficients = newCo;
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation() {
		double[] outCo = new double[coefficients.length - 1];
		for (int deg = 1; deg < coefficients.length; deg++) {
			outCo[deg - 1] = coefficients[deg] * deg;
		}
		return new Polynomial(outCo);
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x) {
		double output = 0;
		for (int deg = 0; deg < coefficients.length; deg++) {
			output += Math.pow(x, deg) * coefficients[deg];
		}
		return output;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x) {
		Polynomial firstDev = this.getFirstDerivation();
		Polynomial secDev = firstDev.getFirstDerivation();
		double epsilon = 0.001;
		return Math.abs(firstDev.computePolynomial(x)) <= epsilon && !(Math.abs(secDev.computePolynomial(x)) <= 0);
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		for (int co = 0; co < coefficients.length; co++) {
			out.append(coefficients[co]).append("x^").append(co).append("+");
		}
		return out.toString();
	}

}
