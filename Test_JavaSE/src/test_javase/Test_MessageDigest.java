/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Administrator
 */
public class Test_MessageDigest {

    public String EncoderByMd5(String source) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //ISO-8859-1 UTF-8           
        String sCode = Base64.encode(md5.digest(source.getBytes("UTF-8")));

        //connectTest(sCode);

        return sCode;
    }

    void connectTest(String str) {
        String driverName = "com.mysql.jdbc.Driver";
        String dbName = "Firsttest";
        String userName = "root";
        String userPasswd = "sa";
        String url = "jdbc:mysql://localhost/" + dbName + "?user="
                + userName + "&password=" + userPasswd;
        String sql = "insert into user values(?,?);";
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            Class.forName(driverName).newInstance();
            connection = DriverManager.getConnection(url);
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "3");
            pstmt.setString(2, str);
            int i = pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                pstmt.close();
                connection.close();
            } catch (Exception ex) {
            }
        }
    }
}
