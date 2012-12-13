/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.login2out;

import com.herograve.user.User;
import com.herograve.util.ContentUtil;
import com.herograve.util.HttpSessionUtil;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Login extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    private String url;
    private User user;
    
    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String execute(){        
       
        //login logic here..
        String psw = request.getParameter("psw");
        if(psw == null){
            request.setAttribute("loginerror", "loginerror");
            return "login";
        }          
        psw = ContentUtil.EncoderByMd5(psw);
        CRUDfromDojo crud = new CRUDfromDojo();
        crud.getInfo(request.getParameter("username"), psw);
        user = crud.getUser();
        
        //login error        
        if(user == null){
            request.setAttribute("loginerror", "loginerror");
            return "login";
        }        
        //login seccess        
        else{
            request.getSession().setAttribute(HttpSessionUtil.USER_KEY, user);
            
            if("topicinfo".equals(request.getParameter("station"))){
                setUrl("topicinfo.jsp?title=" + request.getParameter("title") +"&page=1&areaId=" + request.getParameter("areaId"));
            }
            else if("topicarea".equals(request.getParameter("station"))){
                setUrl("topicarea.jsp?page=" + request.getParameter("page") +  "&areaId=" + request.getParameter("areaId"));
            }             
            else if("index".equals(request.getParameter("station"))){
                //setUrl("welcome.jsp");
                return "index";
            }             
        }
        
        //ps:
        //+ image code check
        
        
        return SUCCESS;
    }
    
    
    
    
    
    
    
    
    

}
