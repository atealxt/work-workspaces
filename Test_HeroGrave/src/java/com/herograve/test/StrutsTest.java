/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.test;

import com.herograve.util.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class StrutsTest extends ActionSupport 
        implements SessionAware, ServletRequestAware, ServletResponseAware{
    
    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response; 
    private List list;
    
    private RequestValidate requestValidate;    
    private int errCode;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
    
    public void setSession(Map att) {
        this.att = att;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }     
    
    @Override
    public String execute() throws ServletException, IOException {
        
        request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, new Date().getTime());                
        System.out.println("struts set seccess!");  
         
        String[] paths = {"com/herograve/test/springconfig.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
        SpringTest st = (SpringTest)ctx.getBean("SpringTest");
        System.out.println("spring set seccess!"); 
        
        String sInsert = request.getParameter("text1");
        if(sInsert != null && !sInsert.trim().equals("")){
            st.insert(request.getParameter("text1"));
        }        
        st.search();
        setList(st.getList());
        
        request.setAttribute("test", "end"); 
        //request.getRequestDispatcher("index.jsp").forward(request, response);
        //response.sendRedirect("index.jsp");        
        return SUCCESS;
    }

    @Override
    public void validate() {  
//        //this block is test!
//        //request.getSession().setAttribute(HttpSessionUtil.LOGIN_KEY, "OK");  
//        if(request.getSession().getAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY) == null){
//            //2008/06/24 15:20:42
//            request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, 1214292042828l); 
//        }        
//        
//        //true validate
//        requestValidate = new RequestValidate(request);
//        if(!requestValidate.validate()){   
//            this.errCode = requestValidate.getErrCode();
//            request.setAttribute("errRedirect", request.getParameter("urlLocation"));
//            addActionError("Request validate error!");
//            //<s:actionerror/>
//        }        
    }
    
    


}













