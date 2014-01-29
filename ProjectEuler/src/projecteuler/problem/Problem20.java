package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem20 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Factorial digit sum";
	}

	@Override
	public String getResult() {
		return String.valueOf(getFactorialDigitSum(100));
	}

	private int getFactorialDigitSum(int max) {
		int sum = 0;
		String factorial = Problem15.factorial(max).toString();
		for (int i = 0; i < factorial.length(); i++) {
			sum += factorial.charAt(i) - 48;
		}
		return sum;
	}
}
