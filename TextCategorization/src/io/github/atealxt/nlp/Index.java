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

		Document d1 = docs.get(2000);
		Document d2 = docs.get(2001);

		List<Double> d1TFIDF = getTFIDF(d1);
		List<Double> d2TFIDF = getTFIDF(d2);

		double cos = innerProducts(d1TFIDF, d2TFIDF) / vectorLen(d1TFIDF, d2TFIDF);
		System.out.println(d1 + " " + d2 + " " + cos);

		// TODO categorization
	}

	private double vectorLen(List<Double> d1tfidf, List<Double> d2tfidf) {
		double dx = 0;
		for (Double d : d1tfidf) {
			dx += Math.pow(d, 2);
		}
		dx = Math.sqrt(dx);
		double dy = 0;
		for (Double d : d2tfidf) {
			dy += Math.pow(d, 2);
		}
		dy = Math.sqrt(dy);
		return dx * dy;
	}

	private double innerProducts(List<Double> d1tfidf, List<Double> d2tfidf) {
		double d = 0;
		for (int i = 0; i < d1tfidf.size(); i++) {
			d += d1tfidf.get(i) * d2tfidf.get(i);
		}
		return d;
	}

	private List<Double> getTFIDF(Document doc) {
		List<Double> list = new ArrayList<Double>();
		for (Term term : terms.values()) {
			double tf = term.getDocs().count(doc);
			double tfidf = tf * term.getIDF();
			list.add(tfidf);
		}
		return list;
	}
}
