package io.github.atealxt.nlp.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharFilter implements Filter {

	private static final Pattern PATTERN_CHAR = Pattern.compile("^\\s*?[/@]*[a-zA-Z]+[a-zA-Z/@]*\\s*$");
	private static final Pattern PATTERN_TRIM = Pattern.compile("\\s+");

	@Override
	public String filter(String input) {
		Matcher m = PATTERN_CHAR.matcher(input);
		if (!m.matches()) {
			return "";
		}
		return PATTERN_TRIM.matcher(input).replaceAll("");
	}
}
