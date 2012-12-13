/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.index;

import com.herograve.user.User;
import com.herograve.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Administrator
 */
public class CRUDfromDojo {
    
    private Session session;
    private Long titleCount;
    private Long replyCount;
    private Long userCount;
    private String latestUserName;
    private List topicarea;

    CRUDfromDojo() {
        //getInfo();
    }
    
    public List getTopicarea() {
        return topicarea;
    }    

    public String getLatestUserName() {
        return latestUserName;
    }

    public Long getReplyCount() {
        return replyCount;
    }

    public Long getTitleCount() {
        return titleCount;
    }

    public Long getUserCount() {
        return userCount;
    }       
    
    public void getInfo(){
        session = HibernateUtil.getSession(); 

        titleCount =  (Long)session.createQuery("select COUNT(topictitle_id) from Topictitle").list().get(0);
        replyCount =  (Long)session.createQuery("select COUNT(topicinfo_id) from Topicinfo").list().get(0);
        userCount = (Long)session.createQuery("select COUNT(user_id) from User").list().get(0); 
        topicarea = session.createQuery("from Topicarea order by topicarea_level").list();        
        
        Query query = session.createQuery("from User order by user_regtime desc");
        query.setFirstResult(0).setMaxResults(1);
        User user = (User)query.list().get(0);
        latestUserName = user.getName();
        

        
        session.close();
    }
    
    
    
    
    
    
   
    
}
