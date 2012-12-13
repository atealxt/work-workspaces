/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.test;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class HibernateTest implements java.io.Serializable{

    private Integer id;
    private String name;
     
    
    public HibernateTest() {
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }      
    
}












