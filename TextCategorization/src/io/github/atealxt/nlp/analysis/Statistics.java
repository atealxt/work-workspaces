package io.github.atealxt.nlp.analysis;

import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.Term;

import java.util.ArrayList;
import java.util.List;

public abstract class Statistics {

	protected final Index index;

	public Statistics(Index index) {
		super();
		this.index = index;
	}

	public abstract void analysis();

	/***
	 * @return dimension 1: array index of term dictionary; dimension 2: tfidf;
	 */
	protected Object[][] getVector(Document doc) {
		List<Object[]> list = new ArrayList<Object[]>();
		int i = -1;
		for (Term term : index.getDict().values()) {
			i++;
			double tf = term.getDocs().count(doc);
			if (tf == 0) {
				continue;
			}
			double tfidf = tf * term.getIDF();
			if (tfidf == 0) {
				continue;
			}
			list.add(new Object[] { i, tfidf });
		}
		return list.toArray(new Object[][] {});
	}

	protected double vectorLen(Document doc1, Object[][] vector1, Document doc2, Object[][] vector2) {
		return vectorLen(doc1, vector1) * vectorLen(doc2, vector2);
	}

	private double vectorLen(Document doc, Object[][] vector) {
		if (doc.getVectorLen() >= 0) {
			return doc.getVectorLen();
		}
		double len = 0;
		for (int i = 0; i < vector.length; i++) {
			len += Math.pow((double) vector[i][1], 2);
		}
		len = Math.sqrt(len);
		doc.setVectorLen(len);
		return len;
	}

	protected double innerProducts(Object[][] vector1, Object[][] vector2) {
		double d = 0;
		int idx2Start = 0;
		for (int i = 0; i < vector1.length; i++) {
			int idx1 = (int) vector1[i][0];
			for (int j = idx2Start; j < vector2.length; j++) {
				int idx2 = (int) vector2[j][0];
				if (idx2 < idx1) {
					continue;
				} else if (idx2 > idx1) {
					idx2Start = j;
					break;
				} else {
					d += (double) vector1[i][1] * (double) vector2[j][1];
					idx2Start = j + 1;
					break;
				}
			}
		}
		return d;
	}
}
