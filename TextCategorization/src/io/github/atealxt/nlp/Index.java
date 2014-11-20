package io.github.atealxt.nlp;

import io.github.atealxt.nlp.analysis.Filter;
import io.github.atealxt.nlp.analysis.Tokenizer;
import io.github.atealxt.nlp.analysis.filter.CaseFilter;
import io.github.atealxt.nlp.analysis.filter.CharFilter;
import io.github.atealxt.nlp.analysis.filter.LengthFilter;
import io.github.atealxt.nlp.analysis.filter.StopWordFilter;
import io.github.atealxt.nlp.analysis.tokenizer.SimpleTokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Index {

	protected Logger logger = LogManager.getLogger(getClass());
	private final static Tokenizer tokenizer;
	private final static List<Filter> filters;
	private final List<Document> docs = new ArrayList<Document>();
	private final Map<String, Term> dict = new HashMap<String, Term>();

	static {
		tokenizer = new SimpleTokenizer();
		filters = new ArrayList<Filter>();
		filters.add(new StopWordFilter());
		filters.add(new CharFilter());
		filters.add(new LengthFilter());
		filters.add(new CaseFilter());
	}

	public void addDoc(String name, String content) {
		addDoc(new Document(name, content));
	}

	public void addDoc(Document doc) {
		docs.add(doc);
		addTerms(doc, doc.getContent());
	}

	public void addTerms(Document doc, String content) {
		for (String term : tokenizer.splitToTerms(content)) {
			addTerm(doc, term);
		}
	}

	private void addTerm(Document doc, String term) {
		String t = filter(term);
		if (t.isEmpty()) {
			logger.trace("Skipped term: {}", term);
			return;
		}
		Term tt = dict.get(t);
		if (tt == null) {
			tt = new Term(t);
			dict.put(t, tt);
		}
		doc.addTerm(tt);
		tt.addDoc(doc, docs.size());
		logger.trace("Added term: {} | {}", t, term);
	}

	private String filter(String term) {
		String t = term;
		for (Filter f : filters) {
			t = f.filter(t);
			if (t.isEmpty()) {
				return t;
			}
		}
		return t;
	}

	public void removeDoc(Document doc) {
		docs.remove(doc);
		for (String term : tokenizer.splitToTerms(doc.getContent())) {
			removeTerm(doc, term);
		}
		doc.clearTerms();
	}

	private void removeTerm(Document doc, String term) {
		String t = filter(term);
		if (t.isEmpty()) {
			return;
		}
		Term tt = dict.get(t);
		if (tt == null) {
			return;
		}
		tt.removeDoc(doc, docs.size());
		if (tt.isNullDoc()) {
			dict.remove(t);
		}
	}

	public List<Document> getDocs() {
		return docs;
	}

	/***
	 * @return dimension 1: array index of term dictionary; dimension 2: tfidf;
	 */
	public Object[][] getVector(Document doc) {
		List<Object[]> list = new ArrayList<Object[]>();
		int i = -1;
		for (Term term : dict.values()) {
			i++;
			int tf = term.count(doc);
			if (tf == 0) {
				continue;
			}
			double tfidf = tf * term.getIDF();
			if (tfidf == 0) {
				continue;
			}
			list.add(new Object[] { i, tfidf });
		}
		return list.toArray(new Object[][] {});
	}

	public void clear() {
		docs.clear();
		dict.clear();
	}
}
