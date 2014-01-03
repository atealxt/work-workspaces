package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem6 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Sum square difference";
	}

	@Override
	public String getURL() {
		return "http://projecteuler.net/problem=6";
	}

	@Override
	public String getResult() {
		return String.valueOf(getSumSquareDifference(100));
	}

	private int getSumSquareDifference(int maxNum) {
		int sumNum = (maxNum + 1) * maxNum / 2;
		int squareOfSum = (int) Math.pow(sumNum, 2);
		int sumOfSquares = 0;
		for (int i = 1; i <= maxNum; i++) {
			sumOfSquares += Math.pow(i, 2);
		}
		return squareOfSum - sumOfSquares;
	}
}
