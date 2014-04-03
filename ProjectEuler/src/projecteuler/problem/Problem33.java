package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem33 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Digit canceling fractions";
	}

	@Override
	public String getResult() {
		return String.valueOf(getDenominator());
	}

	private int getDenominator() {
		int numerator = 1;
		int denominator = 1;
		for (int i = 11; i < 100; i++) {
			for (int j = 10; j < i; j++) {
				if (isCancellable(j, i)) {
					numerator *= j;
					denominator *= i;
				}
			}
		}
		// System.out.println(numerator + "/" + denominator);
		return denominator / numerator;
	}

	private boolean isCancellable(int numerator, int denominator) {
		String n = String.valueOf(numerator);
		String d = String.valueOf(denominator);
		double divide = numerator / (double) denominator;
		for (int i = 0; i < n.length(); i++) {
			char _n = n.charAt(i);
			if (_n == '0') {
				continue;
			}
			for (int j = 0; j < d.length(); j++) {
				char _d = d.charAt(j);
				if (_n != _d) {
					continue;
				}
				int ii = Integer.parseInt(n.substring(1 - i, 1 - i + 1));
				int jj = Integer.parseInt(d.substring(1 - j, 1 - j + 1));
				if (jj == 0) {
					continue;
				}
				double _divide = ii / (double) jj;
				if (divide == _divide) {
					// System.out.println(numerator + "/" + denominator + " " + ii + "/" + jj);
					return true;
				}
			}
		}
		return false;
	}
}
