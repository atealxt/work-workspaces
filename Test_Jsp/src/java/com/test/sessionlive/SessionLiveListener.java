/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.sessionlive;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Administrator
 */
public class SessionLiveListener implements HttpSessionListener,Filter{

    public void sessionCreated(HttpSessionEvent arg0) {
        //do nothing
    }    
    
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("session time out!");
    }

    public void init(FilterConfig arg0) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)res;
        //if(request.getSession(false)==null){
        if(request.getSession().getAttribute("sessionlive")==null){
            //response.sendRedirect(request.getContextPath()+"/index.jsp");            
        }        
        //request.getContextPath(): Test_Jsp
        
        chain.doFilter(request, response);
    }

    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

}




