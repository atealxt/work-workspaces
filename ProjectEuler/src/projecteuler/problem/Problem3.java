package projecteuler.problem;

import java.util.ArrayList;
import java.util.List;

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
		List<Long> primeFactors = new ArrayList<>();
		for (long i = 2; i < number; i++) {
			if (largestPrimeFactor != -1 && largestPrimeFactor * i > number) {
				break;
			}
			if (number % i == 0) {
				if (smallestPrimeFactor == -1) {
					largestPrimeFactor = smallestPrimeFactor = i;
					primeFactors.add(i);
				} else if (i % smallestPrimeFactor == 0) {
					continue;
				} else {
					if (!isPrimeFactor(primeFactors, i)) {
						break;
					}
					primeFactors.add(i);
					largestPrimeFactor = i;
				}
			}
		}
		return largestPrimeFactor;
	}

	private static boolean isPrimeFactor(List<Long> primeFactors, long i) {
		for (Long primeFactor : primeFactors) {
			if (i % primeFactor == 0) {
				return false;
			}
		}
		return true;
	}
}
