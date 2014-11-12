package io.github.atealxt.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Category {

	private final int id;
	private final int dimension;
	private final List<CategorizedDocument> docs = new ArrayList<CategorizedDocument>();
	protected final Set<Term> allTerms = new TreeSet<Term>();

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
		allTerms.addAll(doc.getTerms());
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
		StringBuilder sb = new StringBuilder(50);
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
		allTerms.clear();
	}

	@Override
	public String toString() {
		StringBuilder name = new StringBuilder();
		name.append(dimension).append("_").append(id).append(" ");
		if (allTerms.isEmpty()) {
			if (docs.size() == 1) {
				return name.append(docs.get(0).getName()).toString();
			}
			throw new IllegalStateException();
		}
		name.append("[");
		List<Term> terms = new ArrayList<Term>(allTerms);
		int loop = Math.min(3, terms.size());
		for (int i = terms.size() - 1; i > (terms.size() - 1 - loop); i--) {
			name.append(terms.get(i).getText());
			if (i != terms.size() - loop) {
				name.append(", ");
			}
		}
		name.append("]");
		return name.toString();
	}
}
