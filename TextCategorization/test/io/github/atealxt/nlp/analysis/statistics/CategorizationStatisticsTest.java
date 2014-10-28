package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Document;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class CategorizationStatisticsTest extends TestBase {

	private final int idxStartToCategory = new Random(System.currentTimeMillis()).nextInt(200);
	private final int lenToCategory = 100;

	@Override
	protected void execute() throws ZipException, IOException {

		logger.info("Data Load Start");

		Index index = new Index();
		List<Document> docs = new ArrayList<Document>();

		try (ZipFile file = new ZipFile(new File(getFilePath()));) {
			final Enumeration<? extends ZipEntry> entries = file.entries();
			int sum = 0;
			String docName = null;
			StringBuilder docContent = null;
			while (entries.hasMoreElements()) {
				final ZipEntry entry = entries.nextElement();
				if (entry.isDirectory()) {
					if (docName != null) {
						index.addDoc(docName, docContent.toString());
					}
					docName = entry.getName();
					docContent = new StringBuilder(100);
					sum = 0;
					continue;
				}
				String content = read(file.getInputStream(entry));
				if (sum >= idxStartToCategory && sum < idxStartToCategory + lenToCategory) {
					docs.add(new Document(entry.getName(), content));
				} else {
					docContent.append(content).append(" ");
				}
				sum++;
			}
			index.addDoc(docName, docContent.toString());
		}
		logger.info("Data Load End");
		time();
		logger.info("Analysis Start");
		new CategorizationStatistics(index, docs).analysis();
		logger.info("Analysis End");
		time();
	}
}
