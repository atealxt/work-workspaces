/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.topicinfo;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import com.herograve.topic.*;
import com.herograve.user.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Topicinfo extends ActionSupport implements ServletRequestAware{

    private HttpServletRequest request;
    
    private Topicarea topicarea;
    private Topictitle topictitle;   
    
    private User createUser;
    private Userinfo createUserinfo;
    private Userlevelshow creatUserlevelshow;
    
    private List listTopicinfo;    
    private List<User> listReplyUser = new ArrayList<User>(0);
    private List<Userinfo> listReplyUserinfo = new ArrayList<Userinfo>(0);
    private List<Userlevelshow> listUserlevelshow = new ArrayList<Userlevelshow>(0); 
    private List<ReplyInfo> replyInfo;     

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
    
    private long pageCount;
    
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Userlevelshow getCreatUserlevelshow() {
        return creatUserlevelshow;
    }

    public void setCreatUserlevelshow(Userlevelshow creatUserlevelshow) {
        this.creatUserlevelshow = creatUserlevelshow;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Userinfo getCreateUserinfo() {
        return createUserinfo;
    }

    public void setCreateUserinfo(Userinfo createUserinfo) {
        this.createUserinfo = createUserinfo;
    }

    public List<User> getListReplyUser() {
        return listReplyUser;
    }

    public void setListReplyUser(List<User> listReplyUser) {
        this.listReplyUser = listReplyUser;
    }

    public List<Userinfo> getListReplyUserinfo() {
        return listReplyUserinfo;
    }

    public void setListReplyUserinfo(List<Userinfo> listReplyUserinfo) {
        this.listReplyUserinfo = listReplyUserinfo;
    }

    public List getListTopicinfo() {
        return listTopicinfo;
    }

    public void setListTopicinfo(List listTopicinfo) {
        this.listTopicinfo = listTopicinfo;
    }

    public List<Userlevelshow> getListUserlevelshow() {
        return listUserlevelshow;
    }

    public void setListUserlevelshow(List<Userlevelshow> listUserlevelshow) {
        this.listUserlevelshow = listUserlevelshow;
    }

    public Topicarea getTopicarea() {
        return topicarea;
    }

    public void setTopicarea(Topicarea topicarea) {
        this.topicarea = topicarea;
    }

    public Topictitle getTopictitle() {
        return topictitle;
    }

    public void setTopictitle(Topictitle topictitle) {
        this.topictitle = topictitle;
    }

    public long getPageCount() {
        if(pageCount<=0) return 1;
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }    
        
    public List<ReplyInfo> getReplyInfo() {
        return replyInfo;
    }

    public void setReplyInfo() {
        if(replyInfo == null) replyInfo = new ArrayList<ReplyInfo>();
        ReplyInfo ri=null;

        for(int i = 0;i<listTopicinfo.size();i++){
            ri = new ReplyInfo((com.herograve.topic.Topicinfo)listTopicinfo.get(i), 
                    listReplyUser.get(i), listReplyUserinfo.get(i), listUserlevelshow.get(i));
            replyInfo.add(ri);
        }
    }     

    @Override
    public String execute(){

        if(request.getParameter("title")!=null){
            getDojoData();
        }        
        
        return SUCCESS;
    }      
    
    private void getDojoData(){
        CRUDfromDojo crud = new CRUDfromDojo();
        crud.getInfo(request.getParameter("areaId"), request.getParameter("page"), request.getParameter("title"));
        
        setTopicarea(crud.getTopicarea());
        setTopictitle(crud.getTopictitle());
        setCreateUser(crud.getCreateUser());
        setCreateUserinfo(crud.getCreateUserinfo());
        setCreatUserlevelshow(crud.getCreatUserlevelshow());
        setListTopicinfo(crud.getListTopicinfo());
        setListReplyUser(crud.getListReplyUser());
        setListReplyUserinfo(crud.getListReplyUserinfo());
        setListUserlevelshow(crud.getListUserlevelshow());
        setPageCount(crud.getPageCount());
        setReplyInfo();        
        
    }
}
