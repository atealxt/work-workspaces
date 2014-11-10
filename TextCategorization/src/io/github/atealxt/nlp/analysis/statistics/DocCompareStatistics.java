package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

public class DocCompareStatistics extends Statistics {

	public DocCompareStatistics(Index index) {
		super(index);
	}

	@Override
	public void analysis() {
		Document d1 = index.getDocs().get(0);
		Document d2 = index.getDocs().get(1);

		Object[][] vector1 = index.getVector(d1);
		Object[][] vector2 = index.getVector(d2);

		double cos = innerProducts(vector1, vector2) / vectorLen(d1, vector1, d2, vector2);
		logger.info("{} {} {}", d1, d2, cos);
	}
}
