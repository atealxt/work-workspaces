package com.soa.userbean;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jApp extends HttpServlet {

	public static void main(String[] args) {
		new Log4jApp().TestLog();
	}

	public void TestLog() {
		String path = System.getProperty("user.dir");
		PropertyConfigurator.configure(path	+ "/WebContent/WEB-INF/log4j.properties");
		Logger log = Logger.getLogger("org.zblog.test");
		log.info("测试");
	}
}