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
			if (logger.isDebugEnabled()) {
				logger.debug("Dimension {} categories:", dimension);
			}
			dimension++;
			logger.info("Building iterate {} index", dimension);
			index.clear();
			index = new Index();
			for (Category cat : categories) {
				String name = cat.getDocsName(", ");
				String docContent = cat.getDocsContent(" ");
				cat.clear();
				index.addDoc(new CategorizedDocument(name, docContent.toString()));
			}
			logger.info("Calculate iterate {} categories", dimension);
			superCategories = calcSuperCategories(index, dimension);
		}
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
				logger.trace("{} {} {}", d1, d2, cos);
				if (cos >= THRESHOLD) {
					d2.setCategory(category);
					logger.trace("Category {} - {} {}", categorySequence, d1, d2);
				}
			}
		}

		logger.info("Dimension {} category count: {}", dimension, categories.size());
		return categories;
	}

	private Object[][] getVector(CategorizedDocument doc) {
		if (doc.getVector() != null) {
			return doc.getVector();
		}
		Object[][] ret = index.getVector(doc);
		doc.setVector(ret);
		return ret;
	}
}
