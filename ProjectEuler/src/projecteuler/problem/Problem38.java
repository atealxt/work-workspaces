package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem38 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Pandigital multiples";
	}

	@Override
	public String getResult() {
		return String.valueOf(getLargestPandigitalNum());
	}

	private int getLargestPandigitalNum() {
		for (int i = MAX_NUMBER; i > 0; i--) {
			StringBuilder s = new StringBuilder();
			for (int j = 1;; j++) {
				int x = i * j;
				s.append(x);
				if (s.length() < 9) {
					continue;
				}
				if (s.length() > 9) {
					break;
				}
				int n = Integer.parseInt(s.toString());
				if (Problem32.isPandigital(String.valueOf(n))) {
					return n;
				}
			}
		}
		return -1;
	}

	private static final int MAX_NUMBER = 9876;
}
