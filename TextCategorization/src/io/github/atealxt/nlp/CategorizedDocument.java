package io.github.atealxt.nlp;

import com.google.common.collect.Multiset;

public class CategorizedDocument extends Document {

	private Category category;
	private Object[][] vector;

	public CategorizedDocument(String name, String content) {
		super(name, content);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		if (category != null) {
			category.addDocument(this);
		}
	}

	public Object[][] getVector() {
		return vector;
	}

	public void setVector(Object[][] vector) {
		this.vector = vector;
	}

	Multiset<Term> getTerms() {
		return terms;
	}
}
