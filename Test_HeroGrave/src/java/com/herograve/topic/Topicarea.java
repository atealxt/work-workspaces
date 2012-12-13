/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.topic;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Topicarea implements java.io.Serializable{

    public Topicarea() {
    }
    
    private Integer id;
    private String name;
    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

}
