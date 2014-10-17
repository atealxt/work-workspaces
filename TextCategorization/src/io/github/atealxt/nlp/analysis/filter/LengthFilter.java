package io.github.atealxt.nlp.analysis.filter;

public class LengthFilter implements Filter {

	@Override
	public String filter(String input) {
		if (input.length() < 2) {
			return "";
		}
		if (input.length() > 30) {
			return "";
		}
		return input;
	}
}
