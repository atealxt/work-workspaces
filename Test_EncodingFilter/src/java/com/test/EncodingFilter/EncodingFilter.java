/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.test.EncodingFilter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class EncodingFilter  implements Filter{ 
    /** 默认的字符编码 */ 
    protected String strEncoding = null; 
    private FilterConfig config = null; 
    
    //static Logger logger = Logger.getLogger(EncodingFilter.class.getName());

    public void init(FilterConfig config) throws ServletException { 
        
        this.config = config; 
        this.strEncoding = config.getInitParameter("encoding"); 
             
        /*
        logger.info("***init***");
        logger.info(this.config);
        logger.info(this.strEncoding); 
           */   
    } 
    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException { 
        if (!"ignore".equals(this.strEncoding)) { 
            request.setCharacterEncoding(this.strEncoding); 
        } 
        chain.doFilter(request, response); 
         
        /*
        logger.info("***doFilter***");
        logger.info(this.config);
        logger.info(this.strEncoding);
        */
    } 
     
    public void destroy() { 
        config = null; 
    } 
} 
