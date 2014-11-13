package io.github.atealxt.nlp.analysis.tokenizer;

import io.github.atealxt.nlp.analysis.Tokenizer;

import java.util.regex.Pattern;

public class SimpleTokenizer implements Tokenizer {

	private final Pattern PATTERN_SPACE_OR_PATH = Pattern.compile("\\s+|/+");

	@Override
	public String[] splitToTerms(String content) {
		return PATTERN_SPACE_OR_PATH.split(content);
	}
}
