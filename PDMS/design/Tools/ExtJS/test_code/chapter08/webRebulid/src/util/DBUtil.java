package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	static String jdbcURL = "jdbc:mysql://127.0.0.1:3306/book";
	static String jdbcDriver = "com.mysql.jdbc.Driver";
	static String userName = "root";
	static String password = "root";
	/**
	 * 获取数据库连接对象
	 * @return 数据库连接对象
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(jdbcDriver);
		return DriverManager.getConnection(jdbcURL, userName, password);
	}
}
