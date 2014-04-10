package projecteuler.problem;

import org.junit.Assert;

import projecteuler.ProblemTemplate;

public class Problem36 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Double-base palindromes";
	}

	@Override
	public String getResult() {
		Assert.assertTrue(isDoubleBasePalindrome(585));
		Assert.assertFalse(isDoubleBasePalindrome(123));
		Assert.assertFalse(isDoubleBasePalindrome(131));
		return String.valueOf(getSumOfPalindromes());
	}

	private int getSumOfPalindromes() {
		int sum = 0;
		for (int i = 1; i < 1000000; i += 2) {
			if (isDoubleBasePalindrome(i)) {
				sum += i;
			}
		}
		return sum;
	}

	private boolean isDoubleBasePalindrome(int n) {
		String s10 = String.valueOf(n);
		if (!Problem4.isPalindrome(s10)) {
			return false;
		}
		String s2 = Integer.toBinaryString(n);
		return Problem4.isPalindrome(s2);
	}
}

// you can only check odd numbers... If the number in base ten is even, then it's base two counterpart will look something like 1 ... 0
