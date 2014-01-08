package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem9 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Special Pythagorean triplet";
	}

	@Override
	public String getURL() {
		return "http://projecteuler.net/problem=9";
	}

	@Override
	public String getResult() {
		return String.valueOf(getProductOfPythagoreanTriplet(1000));
	}

	private int getProductOfPythagoreanTriplet(int sum) {
		int a = 0, b = 0, c = 0, m = 0, n = 0;
		for (n = 2;; n++) {
			m = (sum / 2 - (int) Math.pow(n, 2)) / n;
			a = (int) (Math.pow(n, 2) - Math.pow(m, 2));
			b = 2 * n * m;
			c = (int) (Math.pow(n, 2) + Math.pow(m, 2));
			if (a <= 0 || b <= 0 || c <= 0) {
				continue;
			}
			if ((a + b + c) == sum) {
				return a * b * c;
			}
			if ((a + b + c) > sum) {
				break;
			}
		}
		return NOT_FOUND;
	}

	private static final int NOT_FOUND = -1;
}
// Reference: http://www.mathsisfun.com/numbers/pythagorean-triples.html
