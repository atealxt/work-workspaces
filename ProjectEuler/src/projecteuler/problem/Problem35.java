package projecteuler.problem;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem35 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Circular primes";
	}

	@Override
	public String getResult() {
		Assert.assertArrayEquals(new int[] { 1, 2, 3 }, getNumbers(123));
		Assert.assertArrayEquals(new int[] { 1, 3, 2 }, getNumbers(132));
		Assert.assertArrayEquals(new int[] { 5 }, getNumbers(5));
		Assert.assertArrayEquals(new int[] { 123, 231, 312 }, getRotations(123));
		Assert.assertArrayEquals(new int[] { 5 }, getRotations(5));
		Assert.assertEquals(13, getNumOfCircularPrimes(100));
		return String.valueOf(getNumOfCircularPrimes(1000000));
	}

	private int getNumOfCircularPrimes(int max) {
		int[] primeCache = new int[max];
		for (int i = 0; i < primeCache.length; i++) {
			primeCache[i] = EMPTY;
		}
		int count = 0;
		for (int i = 2; i < max; i++) {
			if (primeCache[i] == FALSE) {
				continue;
			}
			int[] numbers = getRotations(i);
			boolean isCircularPrime = true;
			for (int n : numbers) {
				if (primeCache[n] == TRUE) {
					continue;
				}
				if (Problem7.isPrime(n)) {
					primeCache[n] = TRUE;
				} else {
					primeCache[n] = FALSE;
					isCircularPrime = false;
					break;
				}
			}
			if (isCircularPrime) {
				count++;
			}
		}
		return count;
	}

	private int[] getRotations(int n) {
		int[] numbers = getNumbers(n);
		int[] rotations = new int[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			int[] rotation = new int[numbers.length];
			for (int j = 0; j < numbers.length; j++) {
				int x = j + i;
				if (x >= numbers.length) {
					x -= numbers.length;
				}
				rotation[j] = numbers[x];
			}
			rotations[i] = Problem30.parseNumber(rotation);
		}
		return rotations;
	}

	private int[] getNumbers(int i) {
		String[] s = Problem32.DIGIT.split(String.valueOf(i));
		int[] numbers = new int[s.length];
		for (int j = 0; j < numbers.length; j++) {
			numbers[j] = Integer.parseInt(s[j]);
		}
		return numbers;
	}

	private static final int EMPTY = -1;
	private static final int FALSE = 0;
	private static final int TRUE = 1;
}
