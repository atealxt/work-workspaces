/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Administrator
 */
@Stateless
//@Local ({MySessionLocal.class})
//public class MySessionBean implements MySessionLocal {
@Remote ({MySessionRemote.class})
public class MySessionBean implements MySessionRemote {

    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
}
