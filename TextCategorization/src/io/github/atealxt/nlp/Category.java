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

	public String getDocsContent(String join) {
		StringBuilder sb = new StringBuilder(100);
		for (CategorizedDocument doc : docs) {
			sb.append(doc.getContent()).append(join);
		}
		sb.delete(sb.length() - join.length(), sb.length());
		return sb.toString();
	}

	public String getDocsName(String join) {
		StringBuilder sb = new StringBuilder();
		for (CategorizedDocument doc : docs) {
			sb.append(doc.getName()).append(join);
		}
		sb.delete(sb.length() - join.length(), sb.length());
		return sb.toString();
	}

	public void clear() {
		for (CategorizedDocument doc : docs) {
			doc.clearTerms();
			doc.setCategory(null);
			doc.setContent(null);
		}
		docs.clear();
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", dimension=" + dimension + "]";
	}
}
