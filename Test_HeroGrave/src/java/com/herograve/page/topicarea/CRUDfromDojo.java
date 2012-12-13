/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.topicarea;

import com.herograve.util.HibernateUtil;
import org.hibernate.Session;
import com.herograve.topic.Topicarea;
import com.herograve.topic.Topictitle;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @author Administrator
 */
public class CRUDfromDojo {
    
    private Session session;
    private com.herograve.topic.Topicarea topicarea;
    private List listTopictitle;
    private long topictitleCount;
    
    CRUDfromDojo() {
        //getInfo();
    }

    public Topicarea getTopicarea() {
        return topicarea;
    }

    public List getListTopictitle() {
        return listTopictitle;
    }

    public long getTopictitleCount() {
        return topictitleCount;
    }
    
    public void getInfo(String areaId,String page){
        session = HibernateUtil.getSession();
        
        String search = "from Topicarea where topicarea_id = " + areaId;
        topicarea = (Topicarea)session.createQuery(search).list().get(0);       
        
        search = "select COUNT(topictitle_id) from Topictitle where topicarea_id = " + areaId;
        topictitleCount = (Long)session.createQuery(search).list().get(0);
        
        search = "from Topictitle where topicarea_id = " + areaId;
        search += "order by latestreplytime desc,topictitle_id";
        Query query = session.createQuery(search);
        query.setMaxResults(20);
        if(page != null) query.setFirstResult( (Integer.parseInt(page) - 1) * 20 );
        listTopictitle = query.list();  
        Iterator iterator = listTopictitle.iterator();
        Topictitle temp;
        while(iterator.hasNext()){
            temp = (Topictitle)iterator.next();
            temp.setCreateUser(temp.getUser().getName());
            temp.setCreateUserid(temp.getUser().getId());
        }

        session.close();
    }
    
    public Integer getTopicareaLevel(Integer areaId){
        session = HibernateUtil.getSession();
        
        String search = "from Topicarea where topicarea_id = " + areaId;
        topicarea = (Topicarea)session.createQuery(search).list().get(0);          
        
        session.close();        
        return topicarea.getLevel();
    }
    
    
    
    
}
