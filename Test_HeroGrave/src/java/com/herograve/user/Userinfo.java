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
public class Userinfo implements java.io.Serializable{

    public Userinfo() {
    }
    
    private Integer id;
    private String iconpath;
    private Integer sex;
    private Integer article;
    private Integer score;
    private Integer money;
    //private Integer level;
    private Integer online_time;
    
    private User user;
    private Userlevelshow userlevelshow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticle() {
        return article;
    }

    public void setArticle(Integer article) {
        this.article = article;
    }

    public String getIconpath() {
        return iconpath;
    }

    public void setIconpath(String iconpath) {
        this.iconpath = iconpath;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getOnline_time() {
        return online_time;
    }

    public void setOnline_time(Integer online_time) {
        this.online_time = online_time;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Userlevelshow getUserlevelshow() {
        return userlevelshow;
    }

    public void setUserlevelshow(Userlevelshow userlevelshow) {
        this.userlevelshow = userlevelshow;
    }
}
