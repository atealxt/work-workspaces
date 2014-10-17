package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.TestBase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class DocCompareStatisticsTest extends TestBase {

	@Override
	protected void execute() throws ZipException, IOException {
		System.out.println("Data Load Start");
		Index index = new Index();
		URL url = Thread.currentThread().getContextClassLoader().getResource("articles.zip");
		try (ZipFile file = new ZipFile(new File(url.getFile()));) {
			final Enumeration<? extends ZipEntry> entries = file.entries();
			while (entries.hasMoreElements()) {
				final ZipEntry entry = entries.nextElement();
				if (!entry.isDirectory()) {
					String content = read(file.getInputStream(entry));
					index.add(entry.getName(), content);
				}
			}
		}
		System.out.println("Data Load End");
		time();
		System.out.println("Analysis Start");
		new DocCompareStatistics(index).analysis();
		System.out.println("Analysis End");
		time();
	}

}
