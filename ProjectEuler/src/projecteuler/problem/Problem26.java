package projecteuler.problem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem26 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Reciprocal cycles";
	}

	@Override
	public String getResult() {
		Assert.assertEquals(1, getCycleLength("3333333333333333"));// 1/3
		Assert.assertEquals(1, getCycleLength("16666666666666666"));// 1/6
		Assert.assertEquals(6, getCycleLength("07692307692307693"));// 1/13
		Assert.assertEquals(2, getCycleLength("045454545454545456"));// 1/22
		Assert.assertEquals(3, getCycleLength("0033783783783783786"));// 1/296
		int decimalOfLongestCycle = getDecimalOfLongestCycle(1000);
		Assert.assertEquals(983, decimalOfLongestCycle);
		Assert.assertEquals(decimalOfLongestCycle, getDecimalOfLongestCycleMath(1000));
		return String.valueOf(decimalOfLongestCycle);
	}

	private int getDecimalOfLongestCycle(int n) {
		int longestCycle = -1;
		int decimalOfLongestCycle = -1;
		for (int i = 6; i < n; i++) {

			// http://blog.csdn.net/niushuai666/article/details/6691041
			List<Long> primeFactor = getPrimeFactors(i);
			boolean have2Or5 = have2Or5(primeFactor);
			boolean haveOther = haveOther(primeFactor);
			boolean mixed = have2Or5 && haveOther;
			boolean pure = !have2Or5;

			if (mixed || pure) {
				BigDecimal x = BigDecimal.ONE.divide(new BigDecimal(i), i * 2, RoundingMode.HALF_UP);
				int len = getCycleLength(x.toString().substring(2));
				if (len > longestCycle) {
					longestCycle = len;
					decimalOfLongestCycle = i;
				}
			}
		}
		return decimalOfLongestCycle;
	}

	private int getCycleLength(String str) {
		int start = 0;
		do {
			int loop = 1;
			do {
				String piece = str.substring(start, start + loop);
				boolean isCycle = true;
				for (int i = start + loop; i < str.length() - loop; i += loop) {
					if (!piece.equals(str.substring(i, i + loop))) {
						isCycle = false;
						break;
					}
				}
				if (isCycle) {
					return loop;
				}
				++loop;
			} while (start + loop < str.length() - loop);
		} while (++start < str.length() - 1);
		throw new RuntimeException("Cycle not found!");
	}

	/** @see Problem3#getLargestPrimeFactor */
	private List<Long> getPrimeFactors(long number) {
		long smallestPrimeFactor = -1;
		long largestPrimeFactor = -1;
		List<Long> primeFactors = new ArrayList<>();
		long maxFactor = (long) (Math.sqrt(number) + 1);
		for (long i = 2; i < number; i++) {
			if (number % i != 0) {
				continue;
			}
			if (smallestPrimeFactor == -1) {
				largestPrimeFactor = smallestPrimeFactor = i;
				primeFactors.add(i);
				continue;
			}
			if (i % smallestPrimeFactor == 0) {
				continue;
			}
			if (!Problem3.isPrime(primeFactors, i)) {
				break;
			}
			largestPrimeFactor = i;
			primeFactors.add(i);
			if (i > 5) {
				// don't need to calculate any more for this specific problem.
				break;
			}
			if (largestPrimeFactor >= maxFactor) {
				break;
			}
		}
		return primeFactors;
	}

	private boolean have2Or5(List<Long> primeFactor) {
		for (long i : primeFactor) {
			if (i == 2 || i == 5) {
				return true;
			}
		}
		return false;
	}

	private boolean haveOther(List<Long> primeFactor) {
		for (long i : primeFactor) {
			if (i != 2 && i != 5) {
				return true;
			}
		}
		return false;
	}

	private int getDecimalOfLongestCycleMath(int n) {
		int i, j, len, maxlen, maxn = 0;
		maxlen = 0;
		for (i = 2; i < n; i++) {
			int rest = 1;
			int r0;
			for (j = 0; j < i; j++) {
				rest = rest * 10 % i;
			}
			r0 = rest;
			len = 0;
			do {
				rest = rest * 10 % i;
				len++;
			} while (rest != r0);
			if (len > maxlen) {
				maxn = i;
				maxlen = len;
			}
		}
		return maxn;
	}
}
