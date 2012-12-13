package com.soa.base;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.soa.userbean.InitDB;

public class Sequences {

	public static String Automatic(String jndiName,String seqCode){
		String codeId="";
		int currentNumber=0;
		int maxNumber=0;
		SimpleDateFormat sdf=new SimpleDateFormat("yyMMdd");
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		

		String selectSQL="SELECT seq_code, seq_val+1 as seq_val, seq_max FROM order_sequence WHERE seq_code='"+seqCode+"'";
		
		String insertSQL="INSERT INTO order_sequence(seq_code, seq_val, seq_max) VALUES ('"+seqCode+"', 1, 99999)";
		
		try {
			conn=InitDB.InitDB(jndiName);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(selectSQL);
			if(rs.next()){
				currentNumber=rs.getInt("seq_val");
				maxNumber=rs.getInt("seq_max");
				if(currentNumber>=rs.getInt("seq_max")){
					currentNumber=0;
				}
			}else{
				stmt.execute(insertSQL);
			}
			String updateSQL="UPDATE order_sequence SET seq_val="+currentNumber+" WHERE seq_code='"+seqCode+"'";			
			stmt.execute(updateSQL);
			codeId=Integer.toString(currentNumber);
			while(codeId.length()<Integer.toString(maxNumber).length()){
				codeId="0"+codeId;	
			}
			codeId=seqCode+sdf.format(new Date())+"_"+codeId;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){				
				rs.close();
				stmt.close();
				conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return codeId;
	}
}
