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

		Document d1 = docsToCategory.get(7);
		index.addDoc(d1);
		List<Double> vector1 = getVector(d1);
		double max = 0;
		String category = null;

		for (int j = 0; j < index.getDocs().size() - 1; j++) {
			Document d2 = index.getDocs().get(j);
			List<Double> vector2 = getVector(d2);
			double cos = innerProducts(vector1, vector2) / vectorLen(d1, vector1, d2, vector2);
			// System.out.println(d1 + " " + d2 + " " + cos);
			if (max < cos) {
				max = cos;
				category = d2.getName();
			}
		}
		System.out.println("\"" + d1 + "\" is label to \"" + category + "\"");
	}

	public int getProcess() {
		return process;
	}
}
