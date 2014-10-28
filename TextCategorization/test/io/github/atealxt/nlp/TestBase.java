package io.github.atealxt.nlp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public abstract class TestBase {

	protected long startTime;
	protected long stepTime;
	protected Logger logger = LogManager.getLogger(getClass());

	@Test
	public void test() throws Exception {
		logger.info("Test Start");
		startTime = stepTime = System.currentTimeMillis();
		try {
			execute();
		} catch (Exception e) {
			logger.info("Time Cost {}s", (float) (System.currentTimeMillis() - startTime) / 1000);
			e.printStackTrace();
			throw e;
		}
		logger.info("Test End");
		logger.info("Total Time Cost {}s", (float) (System.currentTimeMillis() - startTime) / 1000);
	}

	protected void time() {
		logger.info("Time Cost {}s", (float) (System.currentTimeMillis() - stepTime) / 1000);
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
