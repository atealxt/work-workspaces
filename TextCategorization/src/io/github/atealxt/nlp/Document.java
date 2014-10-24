package io.github.atealxt.nlp;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Document {

	private String name;
	private String content;
	private final Multiset<Term> terms = HashMultiset.create();
	private double vectorLen = -1;

	public Document(String name, String content) {
		super();
		this.name = name;
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void addTerm(Term t) {
		terms.add(t);
	}

	public Multiset<Term> getTerms() {
		return terms;
	}

	@Override
	public String toString() {
		return name;
	}

	public double getVectorLen() {
		return vectorLen;
	}

	public void setVectorLen(double vectorLen) {
		this.vectorLen = vectorLen;
	}
}
