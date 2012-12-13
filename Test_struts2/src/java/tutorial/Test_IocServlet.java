package tutorial;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

//another way to get Session,Request,Response
public class Test_IocServlet extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {
    private String message;
    private Map att;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public String getMessage() {
        return message;
    }

    public void setSession(Map att) {
        this.att = att;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public String execute() {
        //att.put("msg", "Hello World from Session!");

        HttpSession session = request.getSession();
        session.setAttribute("fromsession", "I'm from session!");

        request.setAttribute("fromrequest", "I'm from request!");
        
        return SUCCESS;
    }
}