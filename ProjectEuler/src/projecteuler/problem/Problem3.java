package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem3 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Largest prime factor";
	}

	@Override
	public String getURL() {
		return "http://projecteuler.net/problem=3";
	}

	@Override
	public String getResult() {
		return String.valueOf(getLargestPrimeFactor(600851475143L));
	}

	private long getLargestPrimeFactor(long number) {
		long smallestPrimeFactor = -1;
		long largestPrimeFactor = -1;
		for (long i = 2; i < number; i++) {
			if (number % i == 0) {
				if (smallestPrimeFactor == -1) {
					largestPrimeFactor = smallestPrimeFactor = i;
				} else if (i % smallestPrimeFactor == 0) {
					break;
				} else {
					largestPrimeFactor = i;
				}
			} else if (largestPrimeFactor != -1 && largestPrimeFactor * i > number) {
				break;
			}
		}
		return largestPrimeFactor;
	}
}
