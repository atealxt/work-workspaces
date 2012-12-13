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
public class Usertemp implements java.io.Serializable{

    public Usertemp() {
    }
    
    private Integer id;
    private String path;
    private Integer last_action_time;
    
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLast_action_time() {
        return last_action_time;
    }

    public void setLast_action_time(Integer last_action_time) {
        this.last_action_time = last_action_time;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

}
