package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.CategorizedDocument;
import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.TestBase;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class AutoCategorizationStatisticsTest extends TestBase {

	@Override
	protected void execute() throws ZipException, IOException {

		logger.info("Data Load Start");
		Index index = new Index();
		try (ZipFile file = new ZipFile(new File(getFilePath()));) {
			final Enumeration<? extends ZipEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				final ZipEntry entry = entries.nextElement();
				if (!entry.isDirectory()) {
					String content = read(file.getInputStream(entry));
					index.addDoc(new CategorizedDocument(entry.getName(), content));
				}
			}
		}
		logger.info("Data Load End");
		time();
		logger.info("Analysis Start");
		AutoCategorizationStatistics statistics = new AutoCategorizationStatistics(index);
		statistics.analysis();
		logger.info("Analysis End");
		time();
	}
}
