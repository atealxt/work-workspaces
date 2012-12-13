/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proxy.java;

/**
 *
 * @author Administrator
 */
public class Inform {

    private String name = "";

    public Inform(final String name) {
        this.name = name;
    }

    public void say() {
        System.out.println("OK,I know " + name + ".");
    }
}
