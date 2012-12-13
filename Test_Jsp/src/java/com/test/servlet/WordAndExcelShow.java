/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.servlet;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrator
 */
public class WordAndExcelShow extends HttpServlet{
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        /*
         * application/msword
         * application/vnd.ms-excel
         * application/mspowerpoint
         * ...
        */
        response.setContentType("application/vnd.ms-excel");
        
        //Thread.currentThread().getContextClassLoader().getResource("/");
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("a.xls");        
        byte[] b= new byte[inputStream.available()];
        inputStream.read(b);
        response.getOutputStream().write(b); 

        //can only prompt.
    }    

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {   
        doGet(request,response);
    } 
    
}
