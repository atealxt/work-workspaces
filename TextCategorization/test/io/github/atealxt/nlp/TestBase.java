package io.github.atealxt.nlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.junit.Test;

public abstract class TestBase {

	protected long startTime;
	protected long stepTime;

	@Test
	public void test() throws Exception {
		System.out.println("Test Start");
		startTime = stepTime = System.currentTimeMillis();
		try {
			execute();
		} catch (Exception e) {
			System.out.println("Time Cost " + (float) (System.currentTimeMillis() - startTime) / 1000 + "s");
			e.printStackTrace();
			throw e;
		}
		System.out.println("Test End");
		System.out.println("Total Time Cost " + (float) (System.currentTimeMillis() - startTime) / 1000 + "s");
	}

	protected void time() {
		System.out.println("Time Cost " + (float) (System.currentTimeMillis() - stepTime) / 1000 + "s");
		stepTime = System.currentTimeMillis();
	}

	protected String getFilePath() {
		return Thread.currentThread().getContextClassLoader().getResource("data.zip").getFile();
	}

	protected String read(InputStream inputStream) throws IOException {
		String str = null;
		StringBuilder sb = new StringBuilder();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			while ((str = r.readLine()) != null) {
				sb.append(str).append(" ");
			}
		}
		return sb.toString();
	}

	protected abstract void execute() throws Exception;
}
