package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem28 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Number spiral diagonals";
	}

	@Override
	public String getResult() {
		return String.valueOf(getSumdiagonals(1001));
	}

	private int getSumdiagonals(int length) {
		int sum = 1;
		for (int i = 3; i <= length; i += 2) {
			for (int j = 0; j < 4; j++) {
				sum += i * i - j * (i - 1);
			}
		}
		return sum;
	}
}
