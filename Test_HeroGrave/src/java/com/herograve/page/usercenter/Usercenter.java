/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.usercenter;

import com.herograve.user.User;
import com.herograve.user.Userinfo;
import com.herograve.util.ErrorInfoUtil;
import com.herograve.util.HttpSessionUtil;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Usercenter extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    private int errCode;
    private User user;
    private Userinfo userinfo;    

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
    
    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(Userinfo userinfo) {
        this.userinfo = userinfo;
    }        
    
    @Override
    public String execute(){
       
        getDojoData();        
        
        return SUCCESS;
    }
    
    @Override
    public void validate() {
        if(request.getSession().getAttribute(HttpSessionUtil.USER_KEY) == null){
            this.errCode = ErrorInfoUtil.ERROR_CODE_LOGIN.ordinal();
            request.setAttribute("errRedirect", "Index.action");
            addActionError("Request validate error!");            
        }         
    }    
    
    private void getDojoData(){
        CRUDfromDojo crud = new CRUDfromDojo(request);
        crud.getInfo();
        setUser(crud.getUser());
        setUserinfo(crud.getUserinfo());
        
        
    }
    
}
