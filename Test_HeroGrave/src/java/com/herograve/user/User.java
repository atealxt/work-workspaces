/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.user;

import java.util.Date;

/**
 *
 * @author Administrator
 */
@SuppressWarnings("serial")
public class User implements java.io.Serializable{

    public User() {
    }
    
    private Integer id;
    private String name; 
    private String pwd;
    private String question;
    private String question_answer;
    private String email;
    private Date regtime;
    private Userlevel userlevel;

    public Userlevel getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(Userlevel userlevel) {
        this.userlevel = userlevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_answer() {
        return question_answer;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
}
