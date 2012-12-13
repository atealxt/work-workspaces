/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Test_InterceptorAction extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    
    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }    
    
    @Override
    @SuppressWarnings("unchecked")
    public String execute(){
        
        ActionContext.getContext().getSession().put("aaa", "Interceptor success");
        
        System.out.println("executing");
        System.out.println(request.getParameter("t1"));
        
        return SUCCESS;
    }
    
    @Override
    public void validate(){
        
        System.out.println("validating");
        
    }


    
}
