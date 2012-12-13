package org.thin.keyvalue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * 这可以认为是一个DAO类，直接操作数据库，但是只执行SQL。beanTable无法完成的操作，
 * 可以通过直接传送SQL的执行。该类中每一个执行SQL的方法，都没有close connection的
 * 操作，设计初衷是采用hibernate的OpenSessionInView的思路，进行统一关闭。具体是否
 * 通过过滤器close connection 是各自项目情况而定。
 * 
 * @author Haihong.Wang
 * @version Feb 19, 2010
 *
 */
public class DBAccesser {
	
	private static ThreadLocal<Connection> threadConnections = new ThreadLocal();

	private static boolean showSQL = true;

	public static DBAccesser INSTANCE = new DBAccesser();

	private static String databaseProductName=null;

	/**
	 * 
	 * 这个方法只做update、delete、add并且SQL上代有问号“?”的操作，如果需要事务，需要自己启动和结束。
	 * 用户可以灵活控制。<br/>
	 * 
	 * this method just do <code>update,delete,add </code>,so if it needs transaction.
	 * transaction's begin and commit must be controlled by user, user know where 
	 * and when to do it is correct.
	 * 
	 * 
	 * @param sql
	 * @param values
	 * @return
	 * @throws SQLException
	 */
	public boolean executeSQL(String sql, List<Object> values)
			throws Exception {
		PreparedStatement pstmt = null;
		boolean rslt = false;
		try {
			if (showSQL) {
				System.out.println("thin: "+sql);
				System.out.println(values);
				
			}
			Connection conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			for (int parameterIndex = 0; parameterIndex < values.size(); parameterIndex++) {
				pstmt.setObject(parameterIndex + 1, values.get(parameterIndex));
			}

			rslt = pstmt.execute();
			
		} catch (Exception e) {
			throw e;
		} finally{
			pstmt.close();
		}
		return rslt;
	}

	/**
	 * 该类执行update，delete，add操作
	 * this method just do <code>update,delete,add </code>,so it need transaction
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public boolean executeSQL(String sql) throws Exception {

		PreparedStatement pstmt=null;
		boolean rslt = false;
		try {
			if (showSQL) {
				System.out.println("thin: "+sql);
			}
			Connection conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rslt = pstmt.execute();
			
		} catch (Exception e) {
			throw e;
		} finally{
			pstmt.close();
		}
		return rslt;
	}

	/**
	 * 从Threadlocal中获取Connection
	 * @return
	 */
	public static Connection getConnection() {
		if (threadConnections.get() == null) {
			Connection conn = createConnection();
			threadConnections.set(conn);
			return conn;
		} else {
			return threadConnections.get();
		}
	}

	/**
	 * 仅仅执行查询
	 * just to query
	 * 
	 * @param sql
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> executeQuery(String sql) throws Exception {
		List<Map<String, Object>> datas = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			if (showSQL) {
				System.out.println("thin: "+sql);
			}
			Connection conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			int columnCount = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Map<String, Object> keyvalue = new HashMap();
				for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
					Object value = rs.getObject(columnIndex);
					String key = rs.getMetaData().getColumnName(columnIndex);
					keyvalue.put(key, value);
				}
				datas.add(keyvalue);

			}

		} catch (Exception e) {
			throw e;
		} finally{
			rs.close();
			pstmt.close();
		}
		return datas;
	}

	
	public static boolean isShowSQL() {
		return showSQL;
	}

	public static void setShowSQL(boolean showSQL) {
		DBAccesser.showSQL = showSQL;
	}

	public static void beginTransaction() throws SQLException {
		Connection conn = getConnection();
		conn.setAutoCommit(false);
	}

	public static void commitTransaction() throws SQLException {
		Connection conn = getConnection();
		conn.commit();
		conn.setAutoCommit(true);
	}

	public static void setTransactionIsolation(int level) throws SQLException {
		Connection conn = getConnection();
		conn.setTransactionIsolation(level);
	}
	
	public static String getDatabaseProductName() throws SQLException{
		if(databaseProductName==null){
			Connection conn = DBAccesser.getConnection();
			DatabaseMetaData dbmd  =conn.getMetaData();
			databaseProductName = dbmd.getDatabaseProductName();
		}
		return databaseProductName;
		
	}
	

	/**
	 * do it yourself
	 * 实际开发中这里可以根据数据库链接池的配置，重写该方法。
	 * @return
	 */
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/thin",
					"root", "root");
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:fxq",
//					"fxq", "fxq");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void cleanConnection() {
		try {
			Connection conn = threadConnections.get();
			if(conn!=null){
				conn.close();
				threadConnections.set(null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
