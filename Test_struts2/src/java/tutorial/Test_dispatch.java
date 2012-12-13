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
public class Test_dispatch extends ActionSupport implements ServletRequestAware{
    
    private HttpServletRequest request;
    
    //Action1
    public String act1(){
        request.setAttribute("act", "I'm action 1");
        return SUCCESS;
    }
    
    //Action2
    public String act2(){
        request.setAttribute("act", "I'm action 2");        
        return SUCCESS;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }

}
