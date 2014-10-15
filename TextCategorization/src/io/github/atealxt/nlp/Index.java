package io.github.atealxt.nlp;

import io.github.atealxt.nlp.filter.CaseFilter;
import io.github.atealxt.nlp.filter.CharFilter;
import io.github.atealxt.nlp.filter.Filter;
import io.github.atealxt.nlp.filter.LengthFilter;
import io.github.atealxt.nlp.filter.StopWordFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {

	private final List<Filter> filters;
	private final List<Document> docs = new ArrayList<Document>();
	private final Map<String, Term> terms = new HashMap<String, Term>();

	public Index() {
		filters = new ArrayList<Filter>();
		filters.add(new StopWordFilter());
		filters.add(new CharFilter());
		filters.add(new LengthFilter());
		filters.add(new CaseFilter());
	}

	public void add(String name, String content) {
		Document doc = new Document(name, content);
		docs.add(doc);
		for (String term : content.split(" ")) {
			addTerm(doc, term);
		}
	}

	private void addTerm(Document doc, String term) {
		String t = term;
		for (Filter f : filters) {
			t = f.filter(t);
		}
		if (!t.isEmpty()) {
			Term tt = terms.get(t);
			if (tt == null) {
				tt = new Term(t);
				terms.put(t, tt);
			}
			doc.addTerm(tt);
			tt.addDoc(doc, docs.size());
			// System.out.println("Add: " + t + " | " + term);
		} else {
			// System.out.println("Pass: " + term);
		}
	}

	public void analysis() {
		for (Term term : terms.values()) {
			for (Document doc : docs) {
				double tf = term.getDocs().count(doc);
				if (tf == 0) {
					continue;
				}
				double tfidf = tf * term.getIDF();
//				System.out.println(term.getText() + " | " + doc.getName() + " | " + tfidf);
				// TODO Vector COS
			}
		}
	}
}
