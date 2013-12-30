package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem4 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Largest palindrome product";
	}

	@Override
	public String getURL() {
		return "http://projecteuler.net/problem=4";
	}

	@Override
	public String getResult() {
		return String.valueOf(getLargestPalindrome(3));
	}

	private int getLargestPalindrome(int tenthPower) {
		int start = (int) Math.pow(10, tenthPower - 1);
		int end = (int) Math.pow(10, tenthPower);
		int largestPalindrome = -1;
		for (int i = end - 1; i >= start; i--) {
			for (int j = end - 1; j >= start; j--) {
				int product = i * j;
				if (!isPalindrome(product)) {
					continue;
				}
				if (product > largestPalindrome) {
					largestPalindrome = product;
				}
				break;
			}
		}
		return largestPalindrome;
	}

	private boolean isPalindrome(int number) {
		String src = String.valueOf(number);
		for (int i = 0; i < src.length() / 2; i++) {
			if (src.charAt(i) != src.charAt(src.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}
}
