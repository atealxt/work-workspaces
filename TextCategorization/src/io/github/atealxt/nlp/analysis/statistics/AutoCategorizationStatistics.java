package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.CategorizedDocument;
import io.github.atealxt.nlp.Category;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

import java.util.ArrayList;
import java.util.List;

public class AutoCategorizationStatistics extends Statistics {

	private final double THRESHOLD = 0.1;
	private int categorySequence = 0;

	public AutoCategorizationStatistics(Index index) {
		super(index);
	}

	@Override
	public void analysis() {

		int dimension = 1, categorySize = index.getDocs().size();
		List<Category> categories = calcSuperCategories(index, dimension);
		List<Category> superCategories = categories;
		while (categorySize != superCategories.size()) {
			categories = superCategories;
			categorySize = categories.size();
			logger.debug("Dimension " + dimension + " categories:");
			for (Category cat : categories) {
				logger.debug(cat.getDocs());
			}
			dimension++;
			logger.info("Building iterate " + dimension + " index");
			clearIndex(index);
			index = new Index();
			for (Category cat : categories) {
				StringBuilder docContent = new StringBuilder(100);
				for (CategorizedDocument doc : cat.getDocs()) {
					docContent.append(doc.getContent()).append(" ");
					clearDoc(doc);
				}
				String name = getDocsName(cat.getDocs());
				index.addDoc(new CategorizedDocument(name, docContent.toString()));
				clearSubCategory(cat);
			}
			logger.info("Calculate iterate " + dimension + " categories");
			superCategories = calcSuperCategories(index, dimension);
		}

		// TODO summarize category name
	}

	private String getDocsName(List<CategorizedDocument> docs) {
		StringBuilder name = new StringBuilder();
		for (CategorizedDocument doc : docs) {
			name.append(doc.getName()).append(", ");
		}
		name.delete(name.length() - 2, name.length());
		return name.toString();
	}

	private void clearDoc(CategorizedDocument doc) {
		doc.getTerms().clear();
		doc.setCategory(null);
	}

	private void clearSubCategory(Category cat) {
		cat.getDocs().clear();
	}

	private void clearIndex(Index index) {
		index.getDocs().clear();
		index.getDict().clear();
	}

	private List<Category> calcSuperCategories(Index index, int dimension) {
		List<Category> categories = new ArrayList<Category>();
		for (int i = 0; i < index.getDocs().size(); i++) {
			CategorizedDocument d1 = (CategorizedDocument) index.getDocs().get(i);
			if (d1.getCategory() != null) {
				continue;
			}
			Category category = new Category(++categorySequence, dimension);
			d1.setCategory(category);
			categories.add(category);
			Object[][] vector1 = getVector(d1);
			for (int j = i + 1; j < index.getDocs().size(); j++) {
				CategorizedDocument d2 = (CategorizedDocument) index.getDocs().get(j);
				if (d2.getCategory() != null) {
					continue;
				}
				Object[][] vector2 = getVector(d2);
				double cos = innerProducts(vector1, vector2) / vectorLen(d1, vector1, d2, vector2);
				logger.trace(d1 + " " + d2 + " " + cos);
				if (cos >= THRESHOLD) {
					d2.setCategory(category);
					logger.trace("Category " + categorySequence + " - " + d1 + " " + d2);
				}
			}
		}

		logger.info("Dimension " + dimension + " category count: " + categories.size());
		return categories;
	}

	private Object[][] getVector(CategorizedDocument doc) {
		if (doc.getVector() != null) {
			return doc.getVector();
		}
		Object[][] ret = super.getVector(doc);
		doc.setVector(ret);
		return ret;
	}
}
