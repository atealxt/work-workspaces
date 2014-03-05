package projecteuler.problem;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem3 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Largest prime factor";
	}

	@Override
	public String getResult() {
		Assert.assertEquals(5, getLargestPrimeFactor(10));
		Assert.assertEquals(5, getLargestPrimeFactor(20));
		Assert.assertEquals(11, getLargestPrimeFactor(66));
		Assert.assertEquals(29, getLargestPrimeFactor(13195));
		return String.valueOf(getLargestPrimeFactor(600851475143L));// 6857
	}

	private long getLargestPrimeFactor(long number) {
		long smallestPrimeFactor = -1;
		long largestPrimeFactor = -1;
		List<Long> primeFactors = new ArrayList<>();
		long maxFactor = (long) (Math.sqrt(number) + 1);
		for (long i = 2; i < number; i++) {
			if (number % i != 0) {
				continue;
			}
			if (smallestPrimeFactor == -1) {
				// the first factor is prime
				largestPrimeFactor = smallestPrimeFactor = i;
				continue;
			}
			if (i % smallestPrimeFactor == 0) {
				continue;
			}
			if (!isPrime(primeFactors, i)) {
				break;
			}
			largestPrimeFactor = i;
			if (largestPrimeFactor >= maxFactor) {
				break;
			}
			primeFactors.add(i);
		}
		return largestPrimeFactor;
	}

	static boolean isPrime(List<Long> primeFactors, long factor) {
		for (Long primeFactor : primeFactors) {
			if (factor % primeFactor == 0) {
				return false;
			}
		}
		return true;
	}
}

// why sqrt:
// http://bbs.csdn.net/topics/300142134
// http://stackoverflow.com/questions/5811151/why-do-we-check-upto-the-square-root-of-a-prime-number-to-determine-if-it-is-pri
