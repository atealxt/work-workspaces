package io.github.atealxt.nlp;

import io.github.atealxt.nlp.analysis.statistics.CategorizationStatistics;
import io.github.atealxt.nlp.analysis.statistics.DocCompareStatistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.junit.Test;

public class TextCategorizationTest {

	@Test
	public void calc() throws ZipException, IOException {
		System.out.println("Test Start");
		System.out.println("Data Load Start");
		long rightnow = System.currentTimeMillis();
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
		System.out.println("Time Cost " + (float) (System.currentTimeMillis() - rightnow) / 1000 + "s");
		rightnow = System.currentTimeMillis();
		System.out.println("Analysis Start");
		testDocCompareStatistics(index);
		testCategorizationStatistics(index);
		System.out.println("Analysis End");
		System.out.println("Time Cost " + (float) (System.currentTimeMillis() - rightnow) / 1000 + "s");
		System.out.println("Test End");
	}

	private void testDocCompareStatistics(Index index) {
		new DocCompareStatistics(index).analysis();
	}

	private void testCategorizationStatistics(Index index) {
		CategorizationStatistics statistics = new CategorizationStatistics(index);
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					System.out.println(statistics.getProcess());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		statistics.analysis();
	}

	private String read(InputStream inputStream) throws IOException {
		String str = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			while ((str = r.readLine()) != null) {
				sb.append(str).append(" ");
			}
		}
		return sb.toString();
	}
}
