package io.github.atealxt.nlp;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Term implements Comparable<Term> {

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

	public int count(Document doc) {
		return docs.count(doc);
	}

	public boolean isNullDoc() {
		return docs.size() == 0;
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

	@Override
	public int compareTo(Term o) {
		if (this.idf != 0 && o.getIDF() != 0 && this.idf != o.getIDF()) {
			return (this.idf - o.getIDF()) > 0 ? 1 : -1;
		}
		return this.text.compareTo(o.getText());
	}
}
