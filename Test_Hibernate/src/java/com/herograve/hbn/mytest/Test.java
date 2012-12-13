/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.hbn.mytest;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String id;  
    private String name;
    private Integer type;
    private String memo;

    public Test() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
