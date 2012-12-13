/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Test_ArrayInArray extends ActionSupport{
    
    
    private List<List<Integer>> listTest;

    public List<List<Integer>> getListTest() {
        return listTest;
    }

    public void setListTest(List<List<Integer>> listTest) {
        this.listTest = listTest;
    }
    
    
    @Override
    public String execute(){
        
        listTest = new ArrayList<List<Integer>>();
        for(int i=1;i<=3;i++){
            List<Integer> listTemp = new ArrayList<Integer>();
            for(int j=0;j<3;j++){
                int x=i*(j+1);
                System.out.println(x);                
                listTemp.add(x);
            }
            listTest.add(listTemp);
        }        
        
        return SUCCESS;
    }    

}
