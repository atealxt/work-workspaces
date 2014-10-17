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

	private final static double cosThreshold = 0.5;
	private final int process = 0;

	@Override
	public void analysis() {
		Document d1 = docsToCategory.get(2);
		index.addDoc(d1);
		for (int i = 0; i < index.getDocs().size() - 1; i++) {
			Document d2 = index.getDocs().get(i);
			List<Double> vector1 = getVector(d1);
			List<Double> vector2 = getVector(d2);
			double cos = innerProducts(vector1, vector2) / vectorLen(d1, vector1, d2, vector2);
			System.out.println(d1 + " " + d2 + " " + cos);
		}
	}

	public int getProcess() {
		return process;
	}
}
