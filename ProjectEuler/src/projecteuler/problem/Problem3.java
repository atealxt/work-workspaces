package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem3 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Largest prime factor";
	}

	@Override
	public String getResult() {
		return String.valueOf(getLargestPrimeFactor(600851475143L));
	}

	private long getLargestPrimeFactor(long number) {
		long smallestPrimeFactor = -1;
		long largestPrimeFactor = -1;
		long maxLoop = (long) (Math.sqrt(number) + 1); // Here is why sqrt: http://bbs.csdn.net/topics/300142134
		for (long i = 2; i < maxLoop; i++) {
			if (number % i != 0) {
				continue;
			}
			if (smallestPrimeFactor == -1) {
				largestPrimeFactor = smallestPrimeFactor = i;
			} else if (i % smallestPrimeFactor == 0) {
				break;
			} else {
				largestPrimeFactor = i;
			}
		}
		return largestPrimeFactor;
	}
}
