package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem21 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Amicable numbers";
	}

	@Override
	public String getResult() {
		return String.valueOf(getSumOfAmicableNum(10000));
	}

	private int getSumOfAmicableNum(int max) {
		int sum = 0;
		boolean[] cache = new boolean[max];
		for (int a = 1; a < max; a++) {
			if (cache[a]) {
				continue;
			}
			int b = d(a);
			int aPrime = d(b);
			if (a != aPrime || a == b) {
				continue;
			}
			cache[b] = true;
			sum += a + b;
		}
		return sum;
	}

	private int d(int n) {
		return getSumOfDivisors(n);
	}

	/** @see Problem12#getNumOfFactor */
	static int getSumOfDivisors(int n) {
		int sum = 1;
		int maxLoop = (int) (Math.sqrt(n) + 1);
		for (int i = 2; i < maxLoop; i++) {
			if (n % i == 0) {
				sum += i;
				int divisor = n / i;
				if (divisor != i) {
					sum += divisor;
				}
			}
		}
		return sum;
	}
}
