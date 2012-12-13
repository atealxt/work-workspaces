/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Administrator
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        
        System.out.print("Please input send address(split with ','): ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
        String str = in.readLine();
        if(str != null){
            String[] sArr = str.split(",");
            for(String s: sArr){
                s = s.trim();
                System.out.println("Mail send address: " + s);
                MailRobot robot = new MailRobot(s);
                if(!robot.sending()){
                    System.out.println("Mail send error: " + s);
                }
            }
        }
        
        System.out.println("\nProgram ended.");
        in.readLine();
        in.close();
        
        
    }

}
