/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.topicinfo;

import com.herograve.topic.Topicinfo;
import com.herograve.user.*;
import com.herograve.util.ContentUtil;

/**
 *
 * @author Administrator
 */
public class ReplyInfo {
    
    private com.herograve.topic.Topicinfo topicinfo;    
    private User user;
    private Userinfo userinfo;
    private Userlevelshow userlevelshow;

    public ReplyInfo(com.herograve.topic.Topicinfo topicinfo, User user, Userinfo userinfo, Userlevelshow userlevelshow) {
        this.topicinfo = topicinfo;
        this.user = user;
        this.userinfo = userinfo;
        this.userlevelshow = userlevelshow;
        
        changeShowtext();
    }

    public Topicinfo getTopicinfo() {
        return topicinfo;
    }

    public User getUser() {
        return user;
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }

    public Userlevelshow getUserlevelshow() {
        return userlevelshow;
    }
    
    private void changeShowtext(){
        topicinfo.setText(ContentUtil.pagefilter(topicinfo.getText()));        
    }
    

}
