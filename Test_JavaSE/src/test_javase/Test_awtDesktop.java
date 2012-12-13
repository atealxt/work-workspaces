/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test_javase;

import java.awt.Desktop;
import java.io.File;

/**
 *
 * @author Administrator
 */
public class Test_awtDesktop {
    
    public void test(){
        
        try {
            Desktop desktop = null;
            if (!Desktop.isDesktopSupported()) {
                throw new UnsupportedOperationException("Desktop API is not " +
                                                    "supported on the current platform"); 
            }
            desktop = Desktop.getDesktop();            
            //desktop.edit(new File("D:\\NetBeans 6.1\\README.html"));
            //desktop.mail();
            
            
            
        } catch (Exception ioe) {
            ioe.printStackTrace();
        }         
        
    }

}
