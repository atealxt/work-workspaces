package projecteuler.problem;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem34 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Digit factorials";
	}

	@Override
	public String getResult() {
		Assert.assertTrue(isCurious(145));
		return String.valueOf(getSumOfNumbers());
	}

	private int getSumOfNumbers() {
		int sum = 0;
		int maxLoop = Problem15.factorial(9).intValue();
		for (int i = 10; i < maxLoop; i++) {
			if (isCurious(i)) {
				sum += i;
			}
		}
		return sum;
	}

	private boolean isCurious(int n) {
		int sum = 0;
		int _n = n;
		while (_n > 0) {
			int y = _n - _n / 10 * 10;
			_n = _n / 10;
			sum += Problem15.factorial(y).intValue();
			if (sum > n) {
				return false;
			}
		}
		return sum == n;
	}
}
