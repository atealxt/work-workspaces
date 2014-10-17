package io.github.atealxt.nlp.analysis;

import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.Term;

import java.util.ArrayList;
import java.util.List;

public abstract class Statistics {

	protected final Index index;

	public Statistics(Index index) {
		super();
		this.index = index;
	}

	protected double vectorLen(Document doc, List<Double> tfidf) {
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

	protected double innerProducts(List<Double> d1tfidf, List<Double> d2tfidf) {
		double d = 0;
		for (int i = 0; i < d1tfidf.size(); i++) {
			d += d1tfidf.get(i) * d2tfidf.get(i);
		}
		return d;
	}

	protected List<Double> getVector(Document doc) {
		List<Double> list = new ArrayList<Double>();
		for (Term term : index.getDict().values()) {
			double tf = term.getDocs().count(doc);
			double tfidf = tf * term.getIDF();
			list.add(tfidf);
		}
		return list;
	}

	public abstract void analysis();
}
