package io.github.atealxt.nlp.analysis.statistics;

import io.github.atealxt.nlp.Index;
import io.github.atealxt.nlp.TestBase;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class CategorizationStatisticsTest extends TestBase {

	@Override
	protected void execute() throws ZipException, IOException {

		System.out.println("Data Load Start");

		int readyToCategory = new Random(System.currentTimeMillis()).nextInt(300);
		Map<String, Index> indices = new TreeMap<String, Index>();
		Map<String, String> texts = new TreeMap<String, String>();

		URL url = Thread.currentThread().getContextClassLoader().getResource("articles.zip");
		try (ZipFile file = new ZipFile(new File(url.getFile()));) {
			final Enumeration<? extends ZipEntry> entries = file.entries();
			Index index = new Index();
			int sum = 0;
			while (entries.hasMoreElements()) {
				final ZipEntry entry = entries.nextElement();
				if (entry.isDirectory()) {
					indices.put(entry.getName(), index);
					index = new Index();
					sum = 0;
					continue;
				}
				String content = read(file.getInputStream(entry));
				if (sum == readyToCategory) {
					texts.put(entry.getName(), content);
				} else {
					index.addDoc(entry.getName(), content);
				}
				sum++;
			}
		}
		System.out.println("ready to category: " + texts.keySet());
		System.out.println("Data Load End");
		time();
		System.out.println("Analysis Start");
		// new CategorizationStatistics(index).analysis();
		System.out.println("Analysis End");
		time();
	}
}
