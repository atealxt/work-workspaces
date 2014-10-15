package io.github.atealxt.nlp;

import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;

public class Term implements Comparable<Term> {

	private String text;
	private final Multiset<Document> docs = TreeMultiset.create();
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
	public int compareTo(Term o) {
		return this.getText().compareTo(o.getText());
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
		idf = Math.log10((double) total / docs.elementSet().size());
	}

	public double getIDF() {
		return idf;
	}
}
