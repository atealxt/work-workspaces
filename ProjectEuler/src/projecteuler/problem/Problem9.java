package projecteuler.problem;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem9 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Special Pythagorean triplet";
	}

	@Override
	public String getResult() {
		Assert.assertEquals(60, getProductOfPythagoreanTriplet(12)); // a:3 b:4 c:5
		return String.valueOf(getProductOfPythagoreanTriplet(1000));
	}

	private int getProductOfPythagoreanTriplet(int sum) {
		int a = 0, b = 0, c = 0, m = 0, n = 0;
		for (n = 2;; n++) {
			m = (sum / 2 - (int) Math.pow(n, 2)) / n; // get m by n : a + b + c = sum = n^2 - m^2 + 2nm + n^2 + m^2
			a = (int) (Math.pow(n, 2) - Math.pow(m, 2));
			if (a < 0) {
				continue;
			}
			b = 2 * n * m;
			c = (int) (Math.pow(n, 2) + Math.pow(m, 2));
			if ((a + b + c) == sum) {
				return a * b * c;
			}
		}
	}
}
// Reference: http://www.mathsisfun.com/numbers/pythagorean-triples.html
