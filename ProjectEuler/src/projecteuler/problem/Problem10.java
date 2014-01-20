package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem10 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Summation of primes";
	}

	@Override
	public String getResult() {
		return String.valueOf(getSumOfPrime(2000000));
	}

	private long getSumOfPrime(int max) {
		if (max == 1) {
			return 2;
		}
		long sum = 2;
		long i = 3;
		while (true) {
			if (Problem7.isPrime(i)) {
				sum += i;
			}
			i += 2;// All primes except 2 are odd.
			if (i > max) {
				return sum;
			}
		}
	}
}
// Another way is trade space for time, refer to official overview pdf.
