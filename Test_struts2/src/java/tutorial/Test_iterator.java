/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

/**
 *
 * @author Administrator
 */
public class Test_iterator extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    
    public void setServletRequest(HttpServletRequest arg0) {
        this.request = arg0;
    }
    
    
    @Override
    public String execute(){
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        
        request.setAttribute("inOutAccountList", list);

        return SUCCESS;
    }

}










