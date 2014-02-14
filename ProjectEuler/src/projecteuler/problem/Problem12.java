package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem12 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Highly divisible triangular number";
	}

	@Override
	public String getResult() {
		return String.valueOf(getFirstTriangleNum(500));
	}

	private int getFirstTriangleNum(int numOfDivisors) {
		if (numOfDivisors == 1) {
			return 1;
		}
		int i = 2;
		int sum = 1;
		while (true) {
			sum += i++;
			if (numOfDivisors < getNumOfFactor(sum)) {
				return sum;
			}
		}
	}

	/** @see Problem7#isPrime */
	private int getNumOfFactor(int num) {
		int n = 0;
		int maxLoop = (int) (Math.sqrt(num) + 1);
		for (int i = 2; i < maxLoop; i++) {
			if (num % i == 0) {
				n++;
				int divisor = num / i;
				if (divisor != i) {
					n++;
				}
			}
		}
		return n + 2;
	}
}
