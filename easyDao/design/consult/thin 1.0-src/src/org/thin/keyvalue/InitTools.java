package org.thin.keyvalue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 初始化SingleBeanTable的工具
 * 
 * @author Haihong.Wang
 * @version Feb 20, 2010
 */
public class InitTools {

	public static InitTools INSTANCE = new InitTools();

	public void loadTableProfile(SingleBeanTable beanTable) throws SQLException {
		String tableName = beanTable.getTableName();
		ResultSet rs=null;
		try {
			Connection conn = DBAccesser.getConnection();
			tableName = tableName.toUpperCase();
			
			DatabaseMetaData dmd = conn.getMetaData();
			rs = dmd.getColumns(null, "%", tableName, "%");

			while (rs.next()) {
				String fieldName = rs.getString(4);//
				String type = rs.getString(6);
				beanTable.addColumnNameType(fieldName.toUpperCase(), type.toUpperCase());
			}

			rs = dmd.getPrimaryKeys(null, null, tableName);
			while (rs.next()) {
				String columnName = rs.getString(4);
				beanTable.addPrimarys(columnName.toUpperCase());
			}
		} catch (SQLException e) {
			throw e;
		} finally{
			rs.close();
		}

		
	}

}
