package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem4 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Largest palindrome product";
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
				if (!isPalindrome(String.valueOf(product))) {
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

	static boolean isPalindrome(String s) {
		for (int i = 0; i <= s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
			}
		}
		return true;
	}
}
