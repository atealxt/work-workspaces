package com.soa.userbean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * @author Gavin
 * 
 */
public class InitDB {
	 Connection conn=null;
	 Statement stmt=null;
	 ResultSet rs=null;
	 ResultSetMetaData rsmd=null;
	 static Logger logger=Logger.getLogger(InitDB.class);
	
	//初始化数据库连接
	public static Connection InitDB(String jndiName) {
		Connection conn=null;
		try{
		Context initContext=(Context) new InitialContext().lookup("java:/comp/env");
		DataSource ds=(DataSource) initContext.lookup(jndiName);
		conn=ds.getConnection();
		}catch(Exception e){
			logger.error("链接数据库失败："+e);
		}
		return conn;
	}
	

	//直接执行sql，用于更新，删除，添加
	public boolean execute(String sql,String jndiName){
		
		conn=InitDB.InitDB(jndiName);
		try{
			if(conn!=null){
				stmt=conn.createStatement();
				stmt.execute(sql);
				
				return true;
			}
		}catch(Exception e){
			logger.error("操作数据库失败："+e);
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				logger.error("关闭数据库失败："+e);
			}
		}
		return false;
	}
	
	
	//获取记录数
	public int findCount(String sql,String jndiName){
		conn=InitDB.InitDB(jndiName);
		int rowCount=0;
		try {
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
			rs.last();
			rowCount=rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCount;
	}
	
	//通过sql语句获取记录数,返回List
	public ArrayList<Object> findBySql(String sql,String jndiName){
		
		ArrayList<Object> list=new ArrayList<Object>();
		
		try{
			conn=InitDB.InitDB(jndiName);
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);
			rsmd=rs.getMetaData();
			
			String columnName="";
			Object columnValue=null;

			while(rs.next()){
				String array="";
				array+="{";
				for(int j=1;j<=rsmd.getColumnCount();j++){
					columnName=rsmd.getColumnName(j);
					columnValue=rs.getObject(columnName);
					array+="\""+columnName+"\"";
					array+=":";
					array+="\""+columnValue+"\"";
					if(j<rsmd.getColumnCount()){
						array+=",";
					}else{
						array+="";
					}
				}
				array+="}";
				
				list.add(array);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//把数据集处理成Ext可以接受的格式
	public String toExtJson(String table,String sqlContent,int start,int limit,Object cons,String jndiName){
		String sql=generateSql(table,sqlContent, cons);
		System.out.println(sql);
		int totalNum=findCount(sql, jndiName);
		ArrayList<Object> list=findBySql(sql+" LIMIT "+limit+" OFFSET "+start, jndiName);
		int resultNum=list.size();
		String str="";
		str+="";
		str+="{";
		str+="'totalCount':'"+totalNum+"',";
		str+="'rows':";
		str+="[";
		for(int i=0;i<resultNum;i++){
			str+="";
			str+=list.get(i);
			if(i<resultNum-1){
				str+=",";				
			}else{
				str+="";
			}
		}
		str+="]";
		str+="}";
		return str;
	}
	
	
	// 通过条件生存sql语句
	@SuppressWarnings("unchecked")
	public String generateSql(String table, String sqlContent,Object cons) {
		String sql = "";
		if(sqlContent==null){
			sqlContent="*";
		}
		sql = "select "+sqlContent+" from " + table;
		HashMap hashMap=null;
		if (cons != null) {
			if(cons instanceof HashMap){
				hashMap=(HashMap) cons;
				Set set=hashMap.entrySet();
				Iterator it=set.iterator();
				int k=0;
				while(it.hasNext()){
					Map.Entry me=(Map.Entry) it.next();
					if(k==0){
						sql+=" where ";
						sql+=me.getKey();
						sql+="=";
						sql+=me.getValue();
					}else{
						sql+=" and ";
						sql+=me.getKey();
						sql+="=";
						sql+=me.getValue();
					}
					k++;					
				}
			}else{
				sql+=" where "+cons;
			}
		}

		return sql;

	}
}
