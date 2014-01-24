package projecteuler.problem;

import projecteuler.ProblemTemplate;

public class Problem17 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Number letter counts";
	}

	@Override
	public String getResult() {
		return String.valueOf(getSumOfLetters(1000));
	}

	private int getSumOfLetters(int max) {
		if (max > 1000) {
			throw new UnsupportedOperationException();
		}
		int sum = 0;
		int cardinalLoop = Math.min(max, 19);
		for (int i = 1; i <= cardinalLoop; i++) {
			String s = DIC_ONE_TO_NINETEEN[i];
			sum += s.length();
		}
		int hundredLoop = Math.min(max, 999);
		for (int i = 20; i <= hundredLoop; i++) {
			String s = "";
			int modHundred = i % 100;
			if (i >= 100) {
				s += DIC_ONE_TO_NINETEEN[i / 100] + " hundred";
				if (modHundred != 0) {
					s += " and ";
				}
			}
			if (modHundred != 0) {
				if (modHundred <= 19) {
					s += DIC_ONE_TO_NINETEEN[modHundred];
				} else {
					s += DIC_TWENTY_TO_NINETY[modHundred / 10 - 2];
					int modTen = modHundred % 10;
					if (modTen != 0) {
						s += " " + DIC_ONE_TO_NINETEEN[modTen];
					}
				}
			}
			sum += s.replace(" ", "").length();
		}
		if (max == 1000) {
			String s = "one thousand";
			sum += s.replace(" ", "").length();
		}
		return sum;
	}

	private final static String[] DIC_ONE_TO_NINETEEN = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",//
			"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
	private final static String[] DIC_TWENTY_TO_NINETY = { "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
}
// Reference: http://en.wikipedia.org/wiki/English_numerals
