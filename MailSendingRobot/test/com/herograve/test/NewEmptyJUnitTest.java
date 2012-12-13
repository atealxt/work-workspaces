/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.test;

import com.herograve.mail.MailRobot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class NewEmptyJUnitTest {

    public NewEmptyJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        
//        double d = 5.0/3;
//        System.out.println(d);        
//        
//        BigDecimal bi = new BigDecimal(d);
//        System.out.println(bi);
//        
//        DecimalFormat df = new DecimalFormat("###.00000");
//        System.out.println(df.format(bi));
        
        //MailRobot robot = new MailRobot("b@b.com");
        //robot.sending();
        
        String str = "a@a.com , b@b.com";
        String[] sArr = str.split(",");
        for(String s: sArr)System.out.println("Mail will sent to: " + s);
        
        
        
        
        
        
        
    }

}