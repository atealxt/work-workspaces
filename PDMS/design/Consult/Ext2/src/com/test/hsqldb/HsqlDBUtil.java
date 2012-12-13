/**
 * 
 */
package com.test.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.test.util.PropertiesUtil;

/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: 上海**公司</p>
 * @author 祝明华
 * @version 1.0
 */
public class HsqlDBUtil {
	
	private Connection conn;
	
	public HsqlDBUtil(){
		initDatabase();
	}

	private void initDatabase(){
		try {
			Properties varp = PropertiesUtil.getDatebaseProperties();
			Class.forName(varp.getProperty("dataSource.driverClassName"));
			this.conn = DriverManager.getConnection(varp.getProperty("dataSource.url"),varp.getProperty("dataSource.username"),varp.getProperty("dataSource.password"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void colseDb(){
		
		try {
			Statement st = conn.createStatement();
			st.execute("SHUTDOWN");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List queryForList(String sql){
		List list = new ArrayList();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            Map temp = new HashMap();
            while(rs.next()){
            	temp = new HashMap(); 
                for(int i=1;i<=cc;i++) {
                	//System.out.println(rsmd.getColumnName(i));
                	//System.out.println(rs.getObject(i));
                    temp.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                list.add(temp);
            }
            return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(st!=null){
					st.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Map queryForObject(String sql){
		
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
            int cc = rsmd.getColumnCount();
            Map temp = new HashMap();
            if(rs.next()){
                for(int i=1;i<=cc;i++) {
                    temp.put(rsmd.getCatalogName(i), rs.getObject(i));
                }
            }
            return temp;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if(st!=null){
					st.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int executeUpdate(String sql){
		
		Statement st = null;
		ResultSet rs = null;
        try {
        	st = conn.createStatement();
			return st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        	try {
				if(st!=null){
					st.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}         
        }
        return 0;
	}
	
	public int executeUpdate(String sql,Object[] params){
		
		PreparedStatement pst = null;
		ResultSet rs = null;
        try {
        	pst = conn.prepareStatement(sql);
        	setParamsForPst(pst,params);
			return pst.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
        	try {
				if(pst!=null){
					pst.close();
				}
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}         
        }
        return 0;
	}
	
	public void executeBatch(String sql,List<Object[]> params){
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for (int i=1; i<=params.size();i++) {
				Object[] objs = params.get(i-1);
				setParamsForPst(pst, objs);
				pst.addBatch();
			}
			pst.executeBatch(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  		    

	}
	
	private void setParamsForPst(PreparedStatement pst,Object[] params){
		
		for(int i=1;i<=params.length;i++){
			Object o = params[i-1];
			String typeName = o.getClass().getSimpleName();
			try {
				if("String".equals(typeName)){
					pst.setString(i, o.toString());
				}else if("Integer".equals(typeName)){
					pst.setInt(i, Integer.parseInt(o.toString()));
				}else if("Date".equals(typeName)){
					pst.setDate(i, java.sql.Date.valueOf(o.toString()));
				}else{
					pst.setObject(i, o);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
