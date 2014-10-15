/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proxy.java;

/**
 * A business class
 *
 */
public class DoBusinessImpl implements DoBusiness {

    public void printNothing() {
        System.out.println("Just Say Hello!");
    }

    public void throwException() {
        throw new RuntimeException("throw Exception from DoBusiness.throwException()");
    }
}