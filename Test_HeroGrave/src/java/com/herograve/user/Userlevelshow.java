/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.user;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Userlevelshow implements java.io.Serializable{

    public Userlevelshow() {
    }
    
    private Integer id;
    private String name; 
    
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
