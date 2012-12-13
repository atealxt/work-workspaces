package com.soa.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.soa.userbean.InitDB;

public class GetUnit {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rst = null;
	String sql = "";

	@SuppressWarnings("unchecked")
	public Map getMap(String table, String key, String value) {
		sql = "select " + key + "," + value + " from " + table;
		Map map = new HashMap<String, String>();
		try {
			conn = InitDB.InitDB("jdbc/Collaboration");
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				map.put(rst.getObject(key), rst.getObject(value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					rst.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return map;

	}
}
