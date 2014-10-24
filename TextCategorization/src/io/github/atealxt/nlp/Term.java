package io.github.atealxt.nlp;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Term {

	private String text;
	private final Multiset<Document> docs = HashMultiset.create();
	private double idf;

	public Term(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

	public Multiset<Document> getDocs() {
		return docs;
	}

	public void addDoc(Document doc, int total) {
		docs.add(doc);
		calcIDF(total);
	}

	public void removeDoc(Document doc, int total) {
		docs.remove(doc);
		calcIDF(total);
	}

	private void calcIDF(int total) {
		idf = Math.log10((double) total / docs.elementSet().size());
	}

	public double getIDF() {
		return idf;
	}
}
