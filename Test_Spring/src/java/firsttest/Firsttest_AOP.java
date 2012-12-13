/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package firsttest;

/**
 *
 * @author Administrator
 */
public class Firsttest_AOP {
    
    public void whoSayHello(Firsttest ft){
        System.out.println(ft.getName() + "Said hello!");
    }
    
    public void whoSayHello(){
        System.out.println("whoSayHello?");
    }

}
