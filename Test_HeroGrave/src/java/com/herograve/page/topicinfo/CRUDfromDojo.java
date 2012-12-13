/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.topicinfo;

import com.herograve.topic.*;
import com.herograve.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import com.herograve.user.*;
import java.util.Date;
import java.util.Iterator;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class CRUDfromDojo {

    private Session session;
    
    private Topicarea topicarea;
    private Topictitle topictitle;   
    
    private User createUser;
    private Userinfo createUserinfo;
    private Userlevelshow creatUserlevelshow;
    
    private List listTopicinfo;
    private List<User> listReplyUser = new ArrayList<User>(0);
    private List<Userinfo> listReplyUserinfo = new ArrayList<Userinfo>(0);
    private List<Userlevelshow> listUserlevelshow = new ArrayList<Userlevelshow>(0);   
    
    private Long pageCount;
    
    CRUDfromDojo() {
        //getInfo();
    }

    public Userlevelshow getCreatUserlevelshow() {
        return creatUserlevelshow;
    }

    public User getCreateUser() {
        return createUser;
    }

    public Userinfo getCreateUserinfo() {
        return createUserinfo;
    }

    public List<User> getListReplyUser() {
        return listReplyUser;
    }

    public List<Userinfo> getListReplyUserinfo() {
        return listReplyUserinfo;
    }

    public List getListTopicinfo() {
        return listTopicinfo;
    }

    public List<Userlevelshow> getListUserlevelshow() {
        return listUserlevelshow;
    }

    public Topicarea getTopicarea() {
        return topicarea;
    }

    public Topictitle getTopictitle() {
        return topictitle;
    }

    public Long getPageCount() {
        return pageCount;
    }
    
    public void getInfo(String areaId,String page,String titleId){
        session = HibernateUtil.getSession();
        
        String search = "";
        topictitle = (Topictitle) session.createCriteria(Topictitle.class).setFetchMode("topicarea", FetchMode.JOIN)
                .setFetchMode("topicinfo", FetchMode.JOIN)
                .setFetchMode("user", FetchMode.JOIN).add(Restrictions.eq("id", new Integer(titleId))).uniqueResult();
        topicarea = topictitle.getTopicarea();
        createUser = topictitle.getUser();
        
        createUserinfo = (Userinfo)session.createCriteria(Userinfo.class).setFetchMode("userlevelshow", FetchMode.JOIN)
                .add(Restrictions.eq("id", createUser.getId())).uniqueResult();
        creatUserlevelshow = createUserinfo.getUserlevelshow();        
        
        Criteria criteria = session.createCriteria(com.herograve.topic.Topicinfo.class).setFetchMode("user", FetchMode.JOIN)
                .add(Restrictions.eq("topictitle", topictitle));
        criteria.setMaxResults(20);
        if(page != null) criteria.setFirstResult( (Integer.parseInt(page) - 1) * 20 );
        listTopicinfo = criteria.list();
                
        com.herograve.topic.Topicinfo topicinfoTemp;
        for(Iterator iterator = listTopicinfo.iterator();iterator.hasNext();){
            topicinfoTemp = (com.herograve.topic.Topicinfo)iterator.next();
            listReplyUser.add(topicinfoTemp.getUser());
        }       
        
        for(Iterator iterator = listReplyUser.iterator();iterator.hasNext();){
            User userTemp = (User)iterator.next();
            search = "from Userinfo where user_id = " + userTemp.getId();
            Userinfo userinfoTemp = (Userinfo)session.createQuery(search).list().get(0);
            Userlevelshow userlevelshowTemp = userinfoTemp.getUserlevelshow();
            listReplyUserinfo.add(userinfoTemp);
            listUserlevelshow.add(userlevelshowTemp);
        }  
        
        search = "select COUNT(*) from Topictitle where topictitle_id = " + titleId;
        pageCount = (Long)session.createQuery(search).list().get(0);  
        pageCount =  (long)(Math.ceil((float)pageCount/20));
        
        //update topictitle viewcount
        Transaction transaction = session.beginTransaction();
        Topictitle topictitleTemp = (Topictitle)session.createCriteria(Topictitle.class)
                .add(Restrictions.eq("id", new Integer(titleId))).uniqueResult();
        topictitleTemp.setViewcount(topictitleTemp.getViewcount() + 1);        
        transaction.commit();        
        
        session.close();

    }
    
    /**
     * save reply info
     * @param user com.herograve.user.User <b>reply user</b>
     * @param titleId java.long.String <b>title id</b> 
     * @param text java.long.String <b>reply content</b>
     * @return long <b>page number</b>
     */
    public long saveReply(User user,String titleId,String text){
        
        session = HibernateUtil.getSession();
                
        //update topictitle
        Transaction transaction = session.beginTransaction();
        Date dateTemp = new Date();
        Topictitle topictitleTemp = (Topictitle)session.createCriteria(Topictitle.class)
                .add(Restrictions.eq("id", new Integer(titleId))).uniqueResult(); 
        topictitleTemp.setReplycount(topictitleTemp.getReplycount() + 1);
        topictitleTemp.setLatestreplytime(dateTemp);
        topictitleTemp.setLatestreplyusername(user.getName());
        
        //create topicinfo
        com.herograve.topic.Topicinfo topicinfoTemp = new com.herograve.topic.Topicinfo();
        topicinfoTemp.setReplytime(dateTemp);
        topicinfoTemp.setUser(user);
        topicinfoTemp.setText(text);
        topicinfoTemp.setTopictitle(topictitleTemp);
        session.save(topicinfoTemp);
        
        //update userinfo
        Userinfo userinfoTemp = (Userinfo)session.createCriteria(Userinfo.class)
                .add(Restrictions.eq("id", user.getId())).uniqueResult();
        userinfoTemp.setScore(userinfoTemp.getScore() + 1);
        
        transaction.commit();
        
        String searchTemp = "select COUNT(*) from Topicinfo where topictitle_id = " + titleId;
        Long pageCountTemp = (Long)session.createQuery(searchTemp).list().get(0); 

        session.close();   
        
        return (long)(Math.ceil((float)pageCountTemp/20));
    }
    

    
    
    
    
    
    
    
}
