/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 *
 * @author Administrator
 */
public class Test_Interceptor extends AbstractInterceptor{

    @Override
    public String intercept(ActionInvocation arg0) throws Exception {
        
        System.out.println("intercepting!");
        
        //seccess
        return arg0.invoke();
    }

}
