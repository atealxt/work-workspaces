/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.login2out;

import com.herograve.util.HttpSessionUtil;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Logout extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    private String url;

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String execute(){        
       
        request.getSession().removeAttribute(HttpSessionUtil.USER_KEY);
        
        if("topicinfo".equals(request.getParameter("station"))){
            setUrl("topicinfo.jsp?title=" + request.getParameter("title") +"&page=1&areaId=" + request.getParameter("areaId"));
        }
        else if("topicarea".equals(request.getParameter("station"))){
            setUrl("topicarea.jsp?page=" + request.getParameter("page") +  "&areaId=" + request.getParameter("areaId"));
        }             
        else if("index".equals(request.getParameter("station"))){
            return "index";
        } 
        
        return SUCCESS;
    }
    
    
    
    
    
    
    
    
    

}
