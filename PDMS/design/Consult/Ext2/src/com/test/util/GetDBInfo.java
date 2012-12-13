package com.test.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: �Ϻ�**��˾</p>
 * @author ף����
 * @version 1.0
 */
public class GetDBInfo {

	private Connection conn;
	
	private String dbuser;
	
	public GetDBInfo(){
		initConn();
	}
	
	public void initConn(){

	    try{   
          Properties varp = PropertiesUtil.getDatebaseProperties();
          dbuser = varp.getProperty("dataSource.username");
	      Class.forName(varp.getProperty("dataSource.driverClassName"));
	      conn = DriverManager.getConnection(varp.getProperty("dataSource.url"),varp.getProperty("dataSource.username"),varp.getProperty("dataSource.password"));
	    }catch (ClassNotFoundException e){
	      System.out.println(e.toString());
	    }catch (SQLException e){
	      System.out.println(e.toString());
	    } 

	}
	
	/**
	 * �õ����ݿ����Ϣ
	 * @param tablename ����
	 * @param needSchema �Ƿ���Ҫƥ��ģʽ
	 *  ע�����������Ҫ����Ϊ��ʹ��oracleʱ����ͬ�û�������ͬ��ʱ���������б�����ԣ�
	 *  Ϊtrueʱ����ʹ�ñ������û�����ƥ�䣬���˵�������һ����Ϊfalse�����ˡ�
	 * @return ���ر��ÿ���ֶε����� �б�
	 */
	public List<TableColumns> getDbTableInfo(String tablename,boolean needSchema){
		
		List<TableColumns> tcList = new ArrayList<TableColumns>();
		try {
			DatabaseMetaData dmd = conn.getMetaData();   		
			//Ҫ��ñ����ڵı�Ŀ������""����ζ��û���κα�Ŀ��Null��ʾ���б�Ŀ��
			String catalog = null;
			//Ҫ��ñ����ڵ�ģʽ������""����ζ��û���κ�ģʽ��Null��ʾ����ģʽ���ò������԰������ַ���ͨ�������_����,Ҳ���԰������ַ���ͨ�������%������
			String schema = null;
			if(needSchema){
				schema = dbuser;
			}
			//ָ��Ҫ���ر�����ò���ƥ�����Щ���ò������԰������ַ���ͨ�������_����,Ҳ���԰������ַ���ͨ�������%������
			String tableName = tablename.toUpperCase();
			
			String prikeyCol = null;
			ResultSet prikey = dmd.getPrimaryKeys(catalog, schema, tableName);
			if(prikey.next()){
				prikeyCol = prikey.getString("COLUMN_NAME");
			}
			ResultSet columns = dmd.getColumns(catalog,schema,tableName,null);
			TableColumns tc = null;
			while(columns.next()){
				tc = new TableColumns();
				tc.setTableName(tableName);
				if(prikeyCol.equals(columns.getString("COLUMN_NAME"))){
					tc.setPrikey(true);
				}
				tc.setDbColumnName(columns.getString("COLUMN_NAME"));
				tc.setColumnName(columns.getString("COLUMN_NAME").toLowerCase());
				tc.setColumnType(columns.getString("TYPE_NAME"));
				System.out.println(columns.getString("COLUMN_NAME")+" "+columns.getString("TYPE_NAME"));
				if(tc.getColumnType().equalsIgnoreCase("NUMBER")
						||tc.getColumnType().equalsIgnoreCase("INTEGER")){
					tc.setJavaType("Integer");
				}else if(tc.getColumnType().equalsIgnoreCase("VARCHAR2")
						||tc.getColumnType().equalsIgnoreCase("VARCHAR")){
					tc.setJavaType("String");
				}else if(tc.getColumnType().equalsIgnoreCase("DATE")
						||tc.getColumnType().equalsIgnoreCase("TIMESTAMP")){
					tc.setJavaType("String");
				}
				tc.setColumnSize(columns.getInt("COLUMN_SIZE"));
				tc.setColumnDecimalDigits(columns.getInt("DECIMAL_DIGITS"));
				tc.setRemark(columns.getString("REMARKS"));
				if("YES".equalsIgnoreCase(columns.getString("IS_NULLABLE"))){
					tc.setNullable(true);
				}else{
					tc.setNullable(false);
				}
				tcList.add(tc);
			}

		} catch (SQLException e) {
			System.out.println("��ȡ��ṹ�����쳣");
			e.printStackTrace();
		}
		
		return tcList;
	}
	
}
