package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.CategorizedDocument;
import io.github.atealxt.nlp.Category;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

public class AutoCategorizationStatistics extends Statistics {

	private final double threshold = 0.1;

	public AutoCategorizationStatistics(Index index) {
		super(index);
	}

	@Override
	public void analysis() {

		int dimension = 1;
		int categoryCnt = 0;

		for (int i = 0; i < index.getDocs().size() - 1; i++) {
			CategorizedDocument d1 = (CategorizedDocument) index.getDocs().get(i);
			if (d1.getCategory() != null) {
				continue;
			}
			Category category = new Category(dimension);
			categoryCnt++;
			d1.setCategory(category);
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
					System.out.println("Category " + categoryCnt + " - " + " " + d1 + " " + d2);
				}
			}
		}

		System.out.println("Total category count: " + categoryCnt);

		// TODO convergent category
		// TODO summarize category text
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
