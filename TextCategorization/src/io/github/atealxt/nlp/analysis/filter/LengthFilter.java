package io.github.atealxt.nlp.analysis.filter;

import io.github.atealxt.nlp.analysis.Filter;

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
