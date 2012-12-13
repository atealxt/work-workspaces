package com.soa.base;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public static String CONTEXT_PATH = "";

	public Log4jInit() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
		ServletContext sct = getServletContext();// 获取web上下文
		String prefix = sct.getRealPath("/");// 得到上下文路径绝对地址
		CONTEXT_PATH = prefix;// log4j 配置文件存放目录
		System.out.println("[....Log4j]: 开始log4j初始化...");
		String file = getInitParameter("log4j");// 从web.xml文件中得到log4j配置文件
		System.setProperty("WORKDIR", prefix);// 设置初始化环境变量

		if (file != null) {
			PropertyConfigurator.configure(prefix + "/" + file);// 根据配置文件设置log4j
		}
		System.out.println("[....Log4j]: log4j初始化成功...");
	}

}
