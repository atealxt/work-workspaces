package io.github.atealxt.nlp;

import io.github.atealxt.nlp.analysis.Filter;
import io.github.atealxt.nlp.analysis.Tokenizer;
import io.github.atealxt.nlp.analysis.filter.CaseFilter;
import io.github.atealxt.nlp.analysis.filter.CharFilter;
import io.github.atealxt.nlp.analysis.filter.LengthFilter;
import io.github.atealxt.nlp.analysis.filter.StopWordFilter;
import io.github.atealxt.nlp.analysis.tokenizer.SpaceTokenizer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Index {

	private final List<Filter> filters;
	private final Tokenizer tokenizer;
	private final List<Document> docs = new ArrayList<Document>();
	private final Map<String, Term> dict = new HashMap<String, Term>();
	private final static double cosThreshold = 0.5;

	public Index() {
		filters = new ArrayList<Filter>();
		filters.add(new StopWordFilter());
		filters.add(new CharFilter());
		filters.add(new LengthFilter());
		filters.add(new CaseFilter());
		tokenizer = new SpaceTokenizer();
	}

	public void add(String name, String content) {
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

	public static int analysisCnt = 0;

	public void analysis() {

//		for (int i = 0; i < docs.size(); i++) {
//			for (int j = i + 1; j < docs.size(); j++) {
//				Document d1 = docs.get(i);
//				Document d2 = docs.get(j);
//				List<Double> vector1 = getVector(d1);
//				List<Double> vector2 = getVector(d2);
//				double cos = innerProducts(vector1, vector2) / (vectorLen(d1, vector1) * vectorLen(d2, vector2));
//				System.out.println(d1 + " " + d2 + " " + cos);
//				analysisCnt++;
//			}
//		}

		Document d1 = docs.get(2000);
		Document d2 = docs.get(2001);

		List<Double> vector1 = getVector(d1);
		List<Double> vector2 = getVector(d2);

		double cos = innerProducts(vector1, vector2) / vectorLen(vector1, vector2);
		System.out.println(d1 + " " + d2 + " " + new DecimalFormat("#.##").format(cos));

		// TODO categorization
	}

	private double vectorLen(Document doc, List<Double> tfidf) {
		if (doc.getVectorLen() >= 0) {
			return doc.getVectorLen();
		}
		double len = 0;
		for (Double d : tfidf) {
			len += Math.pow(d, 2);
		}
		len = Math.sqrt(len);
		doc.setVectorLen(len);
		return len;
	}

	private double innerProducts(List<Double> d1tfidf, List<Double> d2tfidf) {
		double d = 0;
		for (int i = 0; i < d1tfidf.size(); i++) {
			d += d1tfidf.get(i) * d2tfidf.get(i);
		}
		return d;
	}

	private List<Double> getVector(Document doc) {
		List<Double> list = new ArrayList<Double>();
		for (Term term : dict.values()) {
			double tf = term.getDocs().count(doc);
			double tfidf = tf * term.getIDF();
			list.add(tfidf);
		}
		return list;
	}
}
