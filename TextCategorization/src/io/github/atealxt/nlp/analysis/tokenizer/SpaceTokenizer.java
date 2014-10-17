package io.github.atealxt.nlp.analysis.tokenizer;

import io.github.atealxt.nlp.Tokenizer;

public class SpaceTokenizer implements Tokenizer {

	@Override
	public String[] splitToTerms(String content) {
		return content.split(" ");
	}
}
