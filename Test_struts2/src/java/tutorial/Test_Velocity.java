/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionSupport;

/**
 *
 * @author Administrator
 */
public class Test_Velocity extends ActionSupport{
    private String name;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String execute() {
        
        System.out.println("asd");
        
        setName("test2_Velocity");
        
        return SUCCESS;
    }

}
