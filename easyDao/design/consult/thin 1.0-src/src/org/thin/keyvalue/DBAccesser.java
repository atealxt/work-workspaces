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
 * �������Ϊ��һ��DAO�ֱ࣬�Ӳ������ݿ⣬����ִֻ��SQL��beanTable�޷���ɵĲ�����
 * ����ͨ��ֱ�Ӵ���SQL��ִ�С�������ÿһ��ִ��SQL�ķ�������û��close connection��
 * ��������Ƴ����ǲ���hibernate��OpenSessionInView��˼·������ͳһ�رա������Ƿ�
 * ͨ��������close connection �Ǹ�����Ŀ���������
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
	 * �������ֻ��update��delete��add����SQL�ϴ����ʺš�?���Ĳ����������Ҫ������Ҫ�Լ������ͽ�����
	 * �û����������ơ�<br/>
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
	 * ����ִ��update��delete��add����
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
	 * ��Threadlocal�л�ȡConnection
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
	 * ����ִ�в�ѯ
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
	 * ʵ�ʿ�����������Ը������ݿ����ӳص����ã���д�÷�����
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
