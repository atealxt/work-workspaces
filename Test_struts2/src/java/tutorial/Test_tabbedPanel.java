/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class Test_tabbedPanel extends ActionSupport{

    private Map<Integer,String> mapTest;

    public Map<Integer, String> getMapTest() {
        return mapTest;
    }

    public void setMapTest(Map<Integer, String> mapTest) {
        this.mapTest = mapTest;
    }
    
    
    
    @Override
    public String execute(){
        
        mapTest = new HashMap<Integer,String>();
        mapTest.put(0, "secret");
        mapTest.put(1, "male");
        mapTest.put(2, "female");

        
        return SUCCESS;
    }      
    
}
