package io.github.atealxt.nlp.filter;

import java.util.regex.Pattern;

public class StopWordFilter implements Filter {

	private static final Pattern PATTERN = Pattern.compile("[.\":'?,;!\\-_#(){}\\[\\]<>$*&%+^=]+");

	@Override
	public String filter(String input) {
		return PATTERN.matcher(input).replaceAll("");
	}
}
