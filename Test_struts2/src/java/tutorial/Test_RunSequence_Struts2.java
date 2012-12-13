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
public class Test_RunSequence_Struts2 extends ActionSupport{

    @Override
    public String execute(){
        
        System.out.println("Test_RunSequence_Struts2!");
        
        return SUCCESS;
    }
}
