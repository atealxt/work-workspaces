/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.usercenter;

import com.herograve.user.User;
import com.herograve.util.ContentUtil;
import com.herograve.util.ErrorInfoUtil;
import com.herograve.util.HttpSessionUtil;
import com.herograve.util.RequestValidate;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class UpdateUserInfo extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    private int errCode;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        request = arg0;
    }
    
    @Override
    public String execute(){
        
        CRUDfromDojo crud = new CRUDfromDojo(request);
        crud.updateInfo();        
       
        
        return SUCCESS;
    }
    
    @Override
    public void validate() {
        
        if(request.getSession().getAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY) == null){
            //2008/06/24 15:20:42
            request.getSession().setAttribute(HttpSessionUtil.LAST_REQ_TIME_KEY, 1214292042828l); 
        }        
        
        RequestValidate requestValidate = new RequestValidate(request);
        if(requestValidate.isErrLogin() || requestValidate.isTooFast()){
            this.errCode = requestValidate.getErrCode();
            request.setAttribute("errRedirect", "Index.action");
            addActionError("Request validate error!");
            return;
        }
        User user = (User)request.getSession().getAttribute(HttpSessionUtil.USER_KEY);
        String pswOld = request.getParameter("pswOld");
        String pswNew = request.getParameter("pswNew");
        String pswRep = request.getParameter("pswRep");
        if("".equals(pswOld))pswOld=null;
        if("".equals(pswNew))pswNew=null;
        if("".equals(pswRep))pswRep=null;
        if(pswOld != null){
            if(!ContentUtil.EncoderByMd5(pswOld).equals(user.getPwd()) 
                    || pswNew==null || pswRep==null || !pswNew.equals(pswRep) || !ContentUtil.isPswInputStyleOk(pswRep)){
                this.errCode = ErrorInfoUtil.ERROR_CODE_PSW.ordinal();
                request.setAttribute("errRedirect", "Index.action");
                addActionError("Request validate error!");
                return;                 
            }          
        }
        else{
            if(pswNew!=null || pswRep!=null){
                this.errCode = ErrorInfoUtil.ERROR_CODE_PSW.ordinal();
                request.setAttribute("errRedirect", "Index.action");
                addActionError("Request validate error!");
                return;                  
            }
        }
        
        
        
        
    }
    
    
    
}
