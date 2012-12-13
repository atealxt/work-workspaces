/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

import java.text.MessageFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Test_MessageFormat {

    public static void main(String[] args) {

        test_one();
        test_two();
        test_three(new String[]{"100","1000"});
    }

    static void test_one() {
        String str = MessageFormat.format("Hello {0}!", "world");
        System.out.println(str);
    }

    static void test_two() {
        int planet = 7;
        String event = "a disturbance in the Force";

        String result = MessageFormat.format(
            "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.",
            planet, new Date(), event);

        System.out.println(result);
    }

    static void test_three(String... strArr) {
        MessageFormat format = new MessageFormat("Apple: {0};Pear: {1};Orangle: {2};");        
        System.out.println(format.format(strArr));        
    }
}
