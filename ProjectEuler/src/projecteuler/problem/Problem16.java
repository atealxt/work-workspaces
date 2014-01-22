package projecteuler.problem;

import java.math.BigInteger;

import projecteuler.ProblemTemplate;

public class Problem16 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Power digit sum";
	}

	@Override
	public String getResult() {
		return String.valueOf(getSumOfDigits(1000));
	}

	private int getSumOfDigits(int exponent) {
		BigInteger pow = new BigInteger("2").pow(exponent);
		int sum = 0;
		while (pow.compareTo(BigInteger.ZERO) > 0) {
			sum += pow.mod(BigInteger.TEN).intValue();
			pow = pow.divide(BigInteger.TEN);
		}
		return sum;
	}
}
