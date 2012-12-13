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
public class Test_RedirectActionStart extends ActionSupport implements ServletRequestAware{
   
    private HttpServletRequest request;

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }    
    
    @Override
    public String execute() throws Exception { 
        request.setAttribute("name", "aaaaa");        
        return "getDataFirst";
    }    
    
}
