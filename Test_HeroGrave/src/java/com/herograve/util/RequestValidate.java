/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.util;

import com.herograve.user.User;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class RequestValidate {

    //request frequency permit
    private final static int REQUEST_FREQUENCY = 3000;  
    
    private HttpSession session;
    private Long requestTime;
    private HttpServletRequest request;
    
    private int errCode;

    public RequestValidate(HttpServletRequest req) {
        this.request=req;
    }    
    
    public int getErrCode() {
        return errCode;
    }    
    
    /*
     * login or not
     */
    public synchronized boolean isErrLogin(){        
        session = request.getSession();        
        if(session.getAttribute(HttpSessionUtil.USER_KEY) == null) {
            this.errCode = ErrorInfoUtil.ERROR_CODE_LOGIN.ordinal();
            return true;                
        }
        return false;
    }
    
    /*
     * request too fast
     */
    public synchronized boolean isTooFast(){
        session = request.getSession();
        requestTime = (Long)session.getAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY);
        
        if(requestTime == null ||
           new Date().getTime()- requestTime.longValue() < REQUEST_FREQUENCY){
            this.errCode = ErrorInfoUtil.ERROR_CODE_FREQUENCY.ordinal();
            return true;
        }
        return false;
    }    
    
    /*
     * Reply text
     */ 
    public boolean isReplyError(){
        String str = request.getParameter("tb_Reply");
        if(str == null){
            this.errCode = ErrorInfoUtil.ERROR_CODE_TEXTCOUNT.ordinal();
            return true;
        }
        str = str.trim();
        //length error   
        int length = str.length();        
        if(length<5 || length>1000){
            this.errCode = ErrorInfoUtil.ERROR_CODE_TEXTCOUNT.ordinal();
            return true;
        }        
        
        return false;
    }
    
    /*
     * toparea limit check
     */
    public boolean isEnterRefused(Integer areaLevel){        
        User user = (User)request.getSession().getAttribute("USER");
        if(user == null) {
            this.errCode = ErrorInfoUtil.ERROR_CODE_LOGIN.ordinal();
            return true;
        }               
        Integer userlevel = user.getUserlevel().getId();
        
        //CHECK
        //ok: userlevel <= topicarea.topicarea_level
        if(userlevel>areaLevel){
            this.errCode = ErrorInfoUtil.ERROR_CODE_ENTERPERMIT.ordinal();
            return true;            
        }

        return false;
    }
    
    
    
    
    /*
     * validate
     */    
    public synchronized boolean validate(){        
        if(isErrLogin()) return false;
        if(isTooFast()) return false;
        if(isReplyError()) return false;
        
        return true;
    }
    
    
    
}













