/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.topic;

import com.herograve.user.User;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class Topictitle implements java.io.Serializable{

    public Topictitle() {
    }
    
    private Integer id;
    private String title;
    private String title_text;
    private Date createtime;
    private Integer viewcount;
    private Integer replycount;
    private Date latestreplytime;
    private String latestreplyusername;
    
    private Topicarea topicarea;
    private User user;
    
    //bu ding 1..
    private String createUser;
    private Integer createUserid;
    
    private Set<Topicinfo> topicinfo = new HashSet<Topicinfo>(0);

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReplycount() {
        return replycount;
    }

    public void setReplycount(Integer replycount) {
        this.replycount = replycount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Topicarea getTopicarea() {
        return topicarea;
    }

    public void setTopicarea(Topicarea topicarea) {
        this.topicarea = topicarea;
    }

    public Integer getViewcount() {
        return viewcount;
    }

    public void setViewcount(Integer viewcount) {
        this.viewcount = viewcount;
    }

    public Date getLatestreplytime() {
        return latestreplytime;
    }

    public void setLatestreplytime(Date latestreplytime) {
        this.latestreplytime = latestreplytime;
    }

    public String getLatestreplyusername() {
        return latestreplyusername;
    }

    public void setLatestreplyusername(String latestreplyusername) {
        this.latestreplyusername = latestreplyusername;
    }

    public String getTitle_text() {
        return title_text;
    }

    public void setTitle_text(String title_text) {
        this.title_text = title_text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    public Set<Topicinfo> getTopicinfo() {
        return topicinfo;
    }

    public void setTopicinfo(Set<Topicinfo> topicinfo) {
        this.topicinfo = topicinfo;
    }
    
    

}
