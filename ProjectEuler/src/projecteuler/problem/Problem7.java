package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem7 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "10001st prime";
	}

	@Override
	public String getResult() {
		return String.valueOf(getPrime(10001));
	}

	private long getPrime(int num) {
		if (num == 1) {
			return 2;
		}
		int numP = 1;
		long i = 3;
		while (true) {
			if (isPrime(i) && ++numP >= num) {
				return i;
			}
			i += 2;// All primes except 2 are odd.
		}
	}

	/** @see Problem3#getLargestPrimeFactor */
	static boolean isPrime(long n) {
		long maxLoop = (long) (Math.sqrt(n) + 1);
		for (long i = 2; i < maxLoop; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
