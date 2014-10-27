package io.github.atealxt.nlp.analysis.filter;

import io.github.atealxt.nlp.analysis.Filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class StopWordFilter implements Filter {

	private static final Pattern PATTERN_PUNCTUATIONS = Pattern.compile("[.\":'?,;!\\-_#(){}\\[\\]<>$*&%+^=]+");
	private static final Set<String> STOPWORDS = new HashSet<String>();

	static {
		String str = null;
		try (InputStream file = new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("stopwords.txt").getFile());
				BufferedReader reader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {
			while ((str = reader.readLine()) != null && !str.isEmpty()) {
				STOPWORDS.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String filter(String input) {
		String term = PATTERN_PUNCTUATIONS.matcher(input).replaceAll("");
		return STOPWORDS.contains(term) ? "" : term;
	}
}
