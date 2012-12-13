/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test_javase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Administrator
 */
public class Test_PreparedStatement {

    void connectTest(){
        String driverName="com.mysql.jdbc.Driver";
        String dbName="Firsttest";
        String userName="root";
	String userPasswd="sa";
        String url= "jdbc:mysql://localhost/" + dbName + "?user="
			    	+ userName + "&password=" + userPasswd;
        String sql ="update testtable1 set number = ? where thing like ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try{
            Class.forName(driverName).newInstance();
            connection=DriverManager.getConnection(url);
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "1");
            pstmt.setString(2, "%ppl%");
            int i = pstmt.executeUpdate();
        }catch(Exception ex){
            System.out.println(ex);
        }
        finally{
            try{
                pstmt.close();
                connection.close();
            }catch(Exception ex){}
        }
        
        
        
    }
    
}
