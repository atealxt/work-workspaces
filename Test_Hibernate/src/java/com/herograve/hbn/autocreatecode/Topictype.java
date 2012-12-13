/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.autocreatecode;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Topictype implements Serializable {

    private final static long serialVersionUID = 1L;
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
