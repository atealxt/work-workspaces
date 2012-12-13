/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.topic;

import com.herograve.user.User;
import java.util.Date;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Topicinfo implements java.io.Serializable{

    public Topicinfo() {
    }
    
    private Integer id;
    private String text;
    private Date replytime;

    private Topictitle topictitle;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getReplytime() {
        return replytime;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Topictitle getTopictitle() {
        return topictitle;
    }

    public void setTopictitle(Topictitle topictitle) {
        this.topictitle = topictitle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
