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
			Object[][] vector1 = index.getVector(d1);

			double max = 0;
			Document category = null;

			for (int j = 0; j < index.getDocs().size() - 1; j++) {
				Document d2 = index.getDocs().get(j);
				Object[][] vector2 = index.getVector(d2);
				double cos = innerProducts(vector1, vector2) / vectorLen(d1, vector1, d2, vector2);
				logger.trace("{} {} {}", d1, d2, cos);
				if (max < cos) {
					max = cos;
					category = d2;
				}
			}
			if (category == null) {
				logger.info("\"{}\" doesn't match any category.", d1);
			} else {
				logger.debug("\"{}\" is labelled \"{}\"", d1, category.getName());
				if (d1.getName().startsWith(category.getName())) {
					testTrue++;
					index.addTerms(category, d1.getContent()); // Add terms to index if category estimate is correct.
				} else {
					logger.info("False match. \"{}\" is labelled \"{}\"", d1, category.getName());
				}
			}
			index.removeDoc(d1);
		}

		logger.info("Test {} docs, {} classified as expect.", docsToCategory.size(), testTrue);
	}
}
