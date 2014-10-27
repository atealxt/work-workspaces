package io.github.atealxt.nlp;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private final int id;
	private final int dimension;
	private final List<CategorizedDocument> docs = new ArrayList<CategorizedDocument>();

	public Category(int id, int dimension) {
		super();
		this.id = id;
		this.dimension = dimension;
	}

	public int getId() {
		return id;
	}

	public int getDimension() {
		return dimension;
	}

	public void addDocument(CategorizedDocument doc) {
		docs.add(doc);
	}

	public List<CategorizedDocument> getDocs() {
		return docs;
	}
}
