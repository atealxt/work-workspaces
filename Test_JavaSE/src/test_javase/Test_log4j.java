/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

import org.apache.log4j.*;

class A {

    static Logger logger = Logger.getLogger(A.class);

    public void doIt() {
        logger.debug("Did it again!");
    }
}

class ErrTestClass {

    static Logger logger = Logger.getLogger(A.class);

    public void doIt() {
        try {
            error1();
        } catch (Exception ex) {
            //logger.error(ex);
            logger.error("出错了",ex);
        }
    }

    private void error1() throws Exception {
        System.out.println("error1");
        error2();
    }

    private void error2() throws Exception {
        System.out.println("error2");
        error3();
    }

    private void error3() throws Exception {
        System.out.println("error3");
        System.out.println(1/0);
    }
}

/**
 *
 * @author Administrator
 */
public class Test_log4j {

    static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String args[]) {
        new ErrTestClass().doIt();
    }

    public static void logging() {
//        BasicConfigurator.configure();
//        PropertyConfigurator.configure("log4j.properties");
//        
//        HTMLLayout layout = new HTMLLayout();
//        WriterAppender appender = null;
//        
//        try {
//         FileOutputStream output = new FileOutputStream("LogOutput.html");
//         appender = new WriterAppender(layout,output);
//        } catch(Exception e) {}        
//        
//        logger.addAppender(appender);        
//        logger.setLevel(Level.ALL);

        logger.info("Entering application.");
        A a = new A();
        a.doIt();
        logger.info("Exiting application.");

        logger.debug("Here is some DEBUG");
        logger.info("Here is some INFO");
        logger.warn("Here is some WARN");
        logger.error("Here is some ERROR");
        logger.fatal("Here is some FATAL");

    }
}
