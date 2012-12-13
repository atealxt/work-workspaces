/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.ejb.landr;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author Administrator
 */
@Stateless
@Remote({RemoteBean.class})
@Local({LocalBean.class})
public class LocalAndRemoteBean implements RemoteBean, LocalBean {

    private int total = 0;
    private int addresult = 0;

    public int Add(int a, int b) {
        addresult = a + b;
        return addresult;
    }

    public int getResult() {
        total += addresult;
        return total;
    }
}
