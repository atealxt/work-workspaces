/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

import java.security.Provider;
import java.security.Security;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

//        String str = "+++++++++===========";
//        str = str.replaceAll("(.)\\1+", "$1");    
//        System.out.println(str);

//        System.out.println(Math.ceil(123.4));
//        System.out.println(Math.floor(123.4));       
//        System.out.println(Math.ceil(-123.4));
//        System.out.println(Math.floor(-123.4));

//        test_PreparedStatement test = new test1_PreparedStatement();
//        test.connectTest();

//        test_Thread.testing();

//        test_StringFilter f = new test_StringFilter();
//        f.doFilter();

//        System.out.println("Prepare to schedule task.");
//        new test_Timer(2);
//        System.out.println("Task scheduled.");        

        Test_MessageDigest tm = new Test_MessageDigest();
        System.out.println(tm.EncoderByMd5("atealxt123"));

//        Test_awtDesktop ta = new Test_awtDesktop();
//        ta.test();

//        Test_NumberFormat tn = new Test_NumberFormat();
//        System.out.println(tn.getArab("五十亿三千零七十五万零六百二十二"));//5030750622
//        System.out.println(tn.getArab("一百一十一"));//111
//        System.out.println(tn.getArab("十三亿三千零十五万零三百一十二"));//1330150312
//        System.out.println(tn.getArab("一亿三千万"));//130000000
//        System.out.println(tn.getArab("三千五百万六十三"));//35000063

//        Test_Timer tt = new Test_Timer(2000);
//        Test_Timer3 tt = new Test_Timer3();
//        tt.beepForAnHour();
       

    }
}
