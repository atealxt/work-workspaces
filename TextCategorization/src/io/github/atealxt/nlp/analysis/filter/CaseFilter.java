package io.github.atealxt.nlp.analysis.filter;

import io.github.atealxt.nlp.analysis.Filter;

public class CaseFilter implements Filter {

	@Override
	public String filter(String input) {
		return input.toLowerCase();
	}
}
