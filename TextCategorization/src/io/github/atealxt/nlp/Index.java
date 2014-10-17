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

public class Index {

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
		Document doc = new Document(name, content);
		docs.add(doc);
		for (String term : tokenizer.splitToTerms(content)) {
			addTerm(doc, term);
		}
	}

	private void addTerm(Document doc, String term) {
		String t = term;
		for (Filter f : filters) {
			t = f.filter(t);
		}
		if (!t.isEmpty()) {
			Term tt = dict.get(t);
			if (tt == null) {
				tt = new Term(t);
				dict.put(t, tt);
			}
			doc.addTerm(tt);
			tt.addDoc(doc, docs.size());
			// System.out.println("Add: " + t + " | " + term);
		} else {
			// System.out.println("Pass: " + term);
		}
	}

	public Map<String, Term> getDict() {
		return dict;
	}

	public List<Document> getDocs() {
		return docs;
	}
}
