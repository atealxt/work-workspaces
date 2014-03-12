package projecteuler.problem;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem29 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Distinct powers";
	}

	@Override
	public String getResult() {
		Assert.assertEquals(15, getNumOfTerms(5));
		Assert.assertEquals(9183, getNumOfTerms(100));

		Assert.assertEquals(-1, logn(2, 6));
		Assert.assertEquals(2, logn(2, 4));
		Assert.assertEquals(3, logn(2, 8));
		Assert.assertEquals(2, logn(4, 16));
		Assert.assertEquals(15, getNumOfTerms2(5));
		Assert.assertEquals(9183, getNumOfTerms2(100));
		return String.valueOf(getNumOfTerms2(100));
	}

	private int getNumOfTerms(int max) {
		Set<BigInteger> terms = new TreeSet<>();
		for (int a = 2; a <= max; a++) {
			for (int b = 2; b <= max; b++) {
				BigInteger x = new BigInteger(String.valueOf(a)).pow(b);
				terms.add(x);
			}
		}
		return terms.size();
	}

	private int getNumOfTerms2(int max) {
		int n = 0;
		for (int a = 2; a <= max; a++) {
			for (int b = 2; b <= max; b++) {
				if (!duplicated(a, b, max)) {
					n++;
				}
			}
		}
		return n;
	}

	private boolean duplicated(int a, int b, int max) {
		int maxLoop = (int) (Math.sqrt(a) + 1);
		for (int i = 2; i < maxLoop; i++) {
			if (a % i != 0) {
				continue;
			}
			int root = logn(i, a);
			if (root == -1) {
				continue;
			}
			int bPlus = b * root;
			if (bPlus <= max) {
				// System.out.print("(" + a + ")^(" + b + ") ");
				// System.out.print("(" + i + "^" + root + ")^(" + b + ") ");
				// System.out.print("(" + i + ")^(" + b + "*" + root + ") ");
				// System.out.print("(" + i + ")^(" + bPlus + ") ");
				// System.out.println();
				return true;
			} else {
				for (int j = (int) Math.sqrt(bPlus); j >= 2; j--) {
					if (bPlus % j != 0) {
						continue;
					}
					if (j == root) {
						continue;
					}
					if (bPlus / j > max) {
						break;
					}
					if (Math.pow(i, j) <= a) {
						// System.out.print("(" + a + ")^(" + b + ") ");
						// System.out.print("(" + i + "^" + root + ")^(" + b + ") ");
						// System.out.print("(" + i + ")^(" + b + "*" + root + ") ");
						// System.out.print("(" + i + ")^(" + bPlus + ") ");
						// System.out.print("(" + i + "^" + j + ")^(" + bPlus + "/" + j + ") ");
						// System.out.print("(" + (int)Math.pow(i, j) + ")^(" + bPlus / j + ") ");
						// System.out.println();
						return true;
					}
				}
				return false;
			}
		}
		return false;
	}

	private int logn(int base, int num) {
		double root = Math.log(num) / Math.log(base);
		if (root % 1 == 0) {
			return (int) root;
		} else {
			return -1;
		}
	}
}

// 4^6 = (2^2)^6 = 2^(2*6) = (2^3)^4 = 8^4
// 8^34 = (2^3)^34 = 2^34*3 = 2^102 = 2^51*2 = (2^2)^51 = 4^51
// 64^18 = (8^2)^18 = 8^18*2 = 8^36
// 32^76 = (2^5)^76 = 2^76*5 = 2^380 = (2^4)^380/4 = 16^95
