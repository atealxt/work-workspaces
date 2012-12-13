/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class JVMHook {

    public static void start() {
        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                System.out.println("freeMemory: " + Runtime.getRuntime().freeMemory());
                System.out.println("JVM ShutDown");
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JVMHook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
