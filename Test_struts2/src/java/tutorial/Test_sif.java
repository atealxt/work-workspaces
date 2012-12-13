package tutorial;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
//import java.io.StringBufferInputStream;

public class Test_sif extends ActionSupport implements ServletRequestAware{
    
    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }    
    
    private HttpServletRequest request;    
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }   
    
    private Integer intTest;

    public Integer getIntTest() {
        return intTest;
    }

    public void setIntTest(Integer intTest) {
        this.intTest = intTest;
    }
    
    private String charTest;

    public String getCharTest() {
        return charTest;
    }

    public void setCharTest(String charTest) {
        this.charTest = charTest;
    }
    
    
    @Override
    public String execute() throws Exception{
        //inputStream = new StringBufferInputStream("Hello World! This is a text string response from a Struts 2 Action.");
        System.out.println(request.getParameter("paraTest"));
        System.out.println(request.getParameter("paraTest2"));
        
        setIntTest(3);
        
        setCharTest("a&amp;b<br/>c");
        
        return SUCCESS;
    }
    

}








