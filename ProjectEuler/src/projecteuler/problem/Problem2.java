package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem2 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Even Fibonacci numbers";
	}

	@Override
	public String getURL() {
		return "http://projecteuler.net/problem=2";
	}

	@Override
	public String getResult() {
		return String.valueOf(sumEvenFibonacci(4000000));
	}

	private int sumEvenFibonacci(int max) {
		int sumEven = 0;
		int x = 1; // make x 1 rather 0 will reduce one time loop.
		int y = 1;
		int tmp = 0;
		while (y <= max) {
			if (y % 2 == 0) {
				sumEven += y;
			}
			tmp = x + y;
			x = y;
			y = tmp;
		}
		return sumEven;
	}
}
