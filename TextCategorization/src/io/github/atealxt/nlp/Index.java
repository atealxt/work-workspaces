package io.github.atealxt.nlp;

import io.github.atealxt.nlp.analysis.Filter;
import io.github.atealxt.nlp.analysis.Tokenizer;
import io.github.atealxt.nlp.analysis.filter.CaseFilter;
import io.github.atealxt.nlp.analysis.filter.CharFilter;
import io.github.atealxt.nlp.analysis.filter.LengthFilter;
import io.github.atealxt.nlp.analysis.filter.StopWordFilter;
import io.github.atealxt.nlp.analysis.tokenizer.SpaceTokenizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Index {

	protected Logger logger = LogManager.getLogger(getClass());
	private final List<Filter> filters;
	private final Tokenizer tokenizer;
	private final List<Document> docs = new ArrayList<Document>();
	private final Map<String, Term> dict = new HashMap<String, Term>();

	public Index() {
		filters = new ArrayList<Filter>();
		filters.add(new StopWordFilter());
		filters.add(new CharFilter());
		filters.add(new LengthFilter());
		filters.add(new CaseFilter());
		tokenizer = new SpaceTokenizer();
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
		if (!t.isEmpty()) {
			Term tt = dict.get(t);
			if (tt == null) {
				tt = new Term(t);
				dict.put(t, tt);
			}
			doc.addTerm(tt);
			tt.addDoc(doc, docs.size());
			logger.trace("Add: {} | {}", t, term);
		} else {
			logger.trace("Pass: {}", term);
		}
	}

	private String filter(String term) {
		String t = term;
		for (Filter f : filters) {
			t = f.filter(t);
		}
		return t;
	}

	public void removeDoc(Document doc) {
		docs.remove(doc);
		for (String term : tokenizer.splitToTerms(doc.getContent())) {
			removeTerm(doc, term);
		}
		doc.getTerms().clear();
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
		if (tt.getDocs().size() == 0) {
			dict.remove(t);
		}
	}

	public Map<String, Term> getDict() {
		return dict;
	}

	public List<Document> getDocs() {
		return docs;
	}
}
