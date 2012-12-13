package tutorial;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class HelloWorld extends ActionSupport {
    private String name;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public String execute() {
        if(getName().equals("a")) return "gif";
        if(!getName().equals("Atea")) return "index";

        ActionContext.getContext().getSession().put("user", "user!");

        /** this way is incorrect,the correct way is "Test3_IocServlet" 
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpSession session = request.getSession(); 
        */
        
        setName("Hello, " + getName() + "!"); 
        return SUCCESS;
    }
    
    @Override
    public void validate(){
        if (getName() == null || getName().trim().equals("")){
            addFieldError("name", "please input name");
        }
    }
}








