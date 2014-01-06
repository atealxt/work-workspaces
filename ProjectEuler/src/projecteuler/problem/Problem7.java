package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem7 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "10001st prime";
	}

	@Override
	public String getURL() {
		return "http://projecteuler.net/problem=7";
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

	private boolean isPrime(long n) {
		long maxLoop = (long) (Math.sqrt(n) + 1); // Here is why sqrt: http://bbs.csdn.net/topics/300142134
		for (long i = 2; i < maxLoop; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
