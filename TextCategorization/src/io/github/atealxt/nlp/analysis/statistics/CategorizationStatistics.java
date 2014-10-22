package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

import java.util.List;

public class CategorizationStatistics extends Statistics {

	private final List<Document> docsToCategory;

	public CategorizationStatistics(Index index, List<Document> docsToCategory) {
		super(index);
		this.docsToCategory = docsToCategory;
	}

	@Override
	public void analysis() {

		int testTrue = 0;

		for (int i = 0; i < docsToCategory.size(); i++) {

			Document d1 = docsToCategory.get(i);
			index.addDoc(d1);
			List<Double> vector1 = getVector(d1);

			double max = 0;
			Document category = null;

			for (int j = 0; j < index.getDocs().size() - 1; j++) {
				Document d2 = index.getDocs().get(j);
				List<Double> vector2 = getVector(d2);
				double cos = innerProducts(vector1, vector2) / vectorLen(d1, vector1, d2, vector2);
				// System.out.println(d1 + " " + d2 + " " + cos);
				if (max < cos) {
					max = cos;
					category = d2;
				}
			}
			if (category == null) {
				System.out.println("\"" + d1 + "\" doesn't match any category.");
			} else {
				// System.out.println("\"" + d1 + "\" is labelled \"" + category.getName() + "\"");
				if (d1.getName().startsWith(category.getName())) {
					testTrue++;
					index.addTerms(category, d1.getContent()); // Add terms to index if category estimate is correct.
				} else {
					System.out.println("False match. \"" + d1 + "\" is labelled \"" + category.getName() + "\"");
				}
			}
			index.removeDoc(d1);
		}

		System.out.println("Test " + docsToCategory.size() + " docs, " + testTrue + " classified as expect.");
	}
}
