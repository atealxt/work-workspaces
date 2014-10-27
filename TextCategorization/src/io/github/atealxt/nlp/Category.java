package io.github.atealxt.nlp;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private final int dimension;
	private final List<CategorizedDocument> docs = new ArrayList<CategorizedDocument>();

	public Category(int dimension) {
		super();
		this.dimension = dimension;
	}

	public int getDimension() {
		return dimension;
	}

	public void addDocument(CategorizedDocument doc) {
		docs.add(doc);
	}
}
