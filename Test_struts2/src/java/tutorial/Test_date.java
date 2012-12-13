/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Test_date extends ActionSupport implements ServletRequestAware{
    
    private HttpServletRequest request;
    
    @Override
    public String execute(){
        request.setAttribute("dateTest", new java.util.Date());
        
        return SUCCESS;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
    
    @Override
    public void validate() { 

    }
    
    
    
    
    
    
    
    
    
}