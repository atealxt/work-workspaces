/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package firsttest;

/**
 *
 * @author Administrator
 */
public class Firsttest {

    private HelloMSG hello;
    private String name;
    
    public Firsttest() {
    }

    public Firsttest(String name) {
        this.name = name;
    }    

    public HelloMSG getHello() {
        return hello;
    }

    public void setHello(HelloMSG hello) {
        this.hello = hello;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void aopTest(){
        System.out.println("aopTest");
    }
    public void aopTest(Firsttest ft){
        System.out.println("aopTest param");
    }    
    
    

}
