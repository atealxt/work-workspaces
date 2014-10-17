package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

public class CategorizationStatistics extends Statistics {

	public CategorizationStatistics(Index index) {
		super(index);
	}

	private final static double cosThreshold = 0.5;
	public static int process = 0;

	@Override
	public void analysis() {
//		for (int i = 0; i < docs.size(); i++) {
//		for (int j = i + 1; j < docs.size(); j++) {
//			Document d1 = docs.get(i);
//			Document d2 = docs.get(j);
//			List<Double> vector1 = getVector(d1);
//			List<Double> vector2 = getVector(d2);
//			double cos = innerProducts(vector1, vector2) / (vectorLen(d1, vector1) * vectorLen(d2, vector2));
//			System.out.println(d1 + " " + d2 + " " + cos);
//			process++;
//		}
//	}

		// TODO categorization
	}

}
