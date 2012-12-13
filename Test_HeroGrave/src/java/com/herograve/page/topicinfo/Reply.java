/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.topicinfo;

import com.herograve.user.User;
import com.herograve.util.HttpSessionUtil;
import com.herograve.util.RequestValidate;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Reply extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    private int errCode;
    private String url;

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String execute(){
        request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, new Date().getTime());       
        
        
        CRUDfromDojo crud = new CRUDfromDojo();
        long returnPage = crud.saveReply((User)request.getSession().getAttribute(HttpSessionUtil.USER_KEY),
                request.getParameter("title"),request.getParameter("tb_Reply"));
        
        //System.out.println("aaaaaaaaaaaaa" + request.getParameter("tb_Reply"));
        
        setUrl(request.getParameter("urlLocation") + "&page=" + returnPage);        
        
        return SUCCESS;
    }    
    
    @Override
    public void validate() {
        if(request.getSession().getAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY) == null){
            //2008/06/24 15:20:42
            request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, 1214292042828l); 
        }
        
        RequestValidate requestValidate = new RequestValidate(request);
        if(!requestValidate.validate()){
            this.errCode = requestValidate.getErrCode();
            request.setAttribute("errRedirect", request.getParameter("urlLocation"));
            addActionError("Request validate error!");
        }
        
        
    }    
    
    
    
    
    
}
