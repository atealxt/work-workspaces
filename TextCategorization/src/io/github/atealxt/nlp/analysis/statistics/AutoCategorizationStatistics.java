package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.CategorizedDocument;
import io.github.atealxt.nlp.Category;
import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

import java.util.ArrayList;
import java.util.List;

public class AutoCategorizationStatistics extends Statistics {

	private final double threshold = 0.1;
	private int categorySequence = 0;

	public AutoCategorizationStatistics(Index index) {
		super(index);
	}

	@Override
	public void analysis() {

		int dimension = 1, categorySize = index.getDocs().size();

		List<Category> categories = calcSuperCategories(index, dimension);
		while (categorySize != categories.size()) {
			dimension++;
			System.out.println("Building iterate " + dimension + " index");
			categorySize = categories.size();
			index = new Index(); // TODO OOM!
			for (Category cat : categories) {
				StringBuilder docContent = new StringBuilder(100);
				for (Document doc : cat.getDocs()) {
					docContent.append(doc.getContent()).append(" ");
				}
				index.addDoc(new CategorizedDocument("Category " + cat.getId(), docContent.toString()));
			}
			System.out.println("Calculate super categories");
			categories = calcSuperCategories(index, dimension);
		}

		// TODO summarize category name
	}

	private List<Category> calcSuperCategories(Index index, int dimension) {
		List<Category> categories = new ArrayList<Category>();
		for (int i = 0; i < index.getDocs().size() - 1; i++) {
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
				// System.out.println(d1 + " " + d2 + " " + cos);
				if (cos >= threshold) {
					d2.setCategory(category);
					System.out.println("Category " + categorySequence + " - " + d1 + " " + d2);
				}
			}
		}

		System.out.println("Dimension " + dimension + " category count: " + categories.size());
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
