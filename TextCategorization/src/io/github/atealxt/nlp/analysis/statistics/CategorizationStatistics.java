package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.analysis.Statistics;

import java.util.List;

public class CategorizationStatistics extends Statistics {

	public CategorizationStatistics(Index index) {
		super(index);
	}

	private final static double cosThreshold = 0.5;
	private int process = 0;

	@Override
	public void analysis() {

		for (int i = 0; i < index.getDocs().size(); i++) {
			for (int j = i + 1; j < index.getDocs().size(); j++) {
				Document d1 = index.getDocs().get(i);
				Document d2 = index.getDocs().get(j);
				List<Double> vector1 = getVector(d1);
				List<Double> vector2 = getVector(d2);
				double cos = innerProducts(vector1, vector2) / vectorLen(d1, vector1, d2, vector2);
//				System.out.println(d1 + " " + d2 + " " + cos);
				process++;
			}
		}

		// TODO categorization
	}

	public int getProcess() {
		return process;
	}
}
