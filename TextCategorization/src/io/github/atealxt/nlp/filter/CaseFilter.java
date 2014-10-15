package io.github.atealxt.nlp.filter;

public class CaseFilter implements Filter {

	@Override
	public String filter(String input) {
		return input.toLowerCase();
	}
}
