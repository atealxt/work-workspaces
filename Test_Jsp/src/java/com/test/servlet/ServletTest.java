/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.servlet;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class ServletTest extends HttpServlet {

    static Logger logger = Logger.getLogger(ServletTest.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(ServletTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger.debug("logger!");
        System.out.println(logger);
        System.out.println(logger.getName());
        System.out.println(logger.getParent());
        System.out.println(logger.getResourceBundle());
        System.out.println(logger.getLoggerRepository());

        request.setAttribute("setAtt", "Attribute!");

//        PrintWriter out = response.getWriter();
//        out.println("this is servlet test!");

        //header test
        Enumeration enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String s = (String) enumeration.nextElement();
            System.out.println(s + ": " + request.getHeader(s));
        }

        //error test
//        response.setStatus(response.SC_UNAUTHORIZED);//need code but have not get.no admit


//        response.sendRedirect(request.getContextPath()+"/test_servlet.jsp");
        request.getRequestDispatcher("/test_servlet.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
