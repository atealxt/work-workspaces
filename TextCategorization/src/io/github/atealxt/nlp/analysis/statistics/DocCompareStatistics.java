package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

import java.util.List;

public class DocCompareStatistics extends Statistics {

	public DocCompareStatistics(Index index) {
		super(index);
	}

	@Override
	public void analysis() {
		Document d1 = index.getDocs().get(0);
		Document d2 = index.getDocs().get(1);

		List<Double> vector1 = getVector(d1);
		List<Double> vector2 = getVector(d2);

		double cos = innerProducts(vector1, vector2) / (vectorLen(d1, vector1) * vectorLen(d2, vector2));
		System.out.println(d1 + " " + d2 + " " + cos);
	}
}
