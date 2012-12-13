package com.soa.userbean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

class MapTest {
	static Connection conn=null;
	static Statement stmt=null;
	static ResultSet rs=null;
	static ResultSetMetaData rsmd=null;	
	public static void main(String[] args) throws Exception{
		new Log4jApp().TestLog();
		/*
		 * HashMap hm = new HashMap(); hm.put("one", "aaa"); hm.put("two",
		 * "bbb"); hm.put("three", "ccc"); hm.put("four", "ddd");
		 * hm.remove("three"); hm.put("five", "eee"); Set set = hm.entrySet();
		 * Iterator it = set.iterator(); while (it.hasNext()) { Map.Entry me =
		 * (Map.Entry) it.next(); System.out.println(me.getKey() + ":" +
		 * me.getValue() + ":" + me.hashCode()); }
		 * 
		 * 
		 * HashMap<String, Object> cons=new HashMap<String, Object>();
		 * cons.put("unit_id","001"); cons.put("unit_code", "'A03'");
		 * cons.put("unit_name", "'信息部'");
		 * 
		 * InitDB init=new InitDB();
		 * 
		 * System.out.println(init.generateSql("unit_info", cons));
		 */

		Class.forName("org.postgresql.Driver").newInstance();
		String url = "jdbc:postgresql://localhost:5432/Fortune-Sun";
		// soft为你的数据库名
		String user = "pgsql_pisa";
		String password = "wwwno1";
		conn = DriverManager.getConnection(url, user, password);
		String sql = "select * from unit_info limit 10";
		ArrayList<Object> list=findBySql(sql);
		System.out.println(list.get(1));
	}
	
	//通过sql语句获取记录数,返回List
	public static ArrayList<Object> findBySql(String sql){
		
		ArrayList<Object> list=new ArrayList<Object>();
		
		try{
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs=stmt.executeQuery(sql);
			rsmd=rs.getMetaData();
			
			String columnName="";
			Object columnValue=null;
			while(rs.next()){
				String array="";
				array+="{";
				for(int j=1;j<rsmd.getColumnCount();j++){
					columnName=rsmd.getColumnName(j);
					columnValue=rs.getObject(columnName);
					array+="'"+columnName+"'";
					array+=":";
					array+="'"+columnValue+"'";
					if(j<rsmd.getColumnCount()-1){
						array+=",";
					}else{
						array+="";
					}
				}
				array+="}";
				System.out.println(array);
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
		
		System.out.println(conn!=null);
		return list;
	}	
}