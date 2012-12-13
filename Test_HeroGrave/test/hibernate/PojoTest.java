/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate;

import com.herograve.user.*;
import com.herograve.topic.*;
import com.herograve.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.hibernate.Hibernate;

/**
 *
 * @author Administrator
 */
public class PojoTest {

    public PojoTest() {
    }    

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    //@Test
    public void userlevel() {       
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();
        
        Userlevel userlevel = null;
        List list = session.createQuery("from Userlevel").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            userlevel = (Userlevel) iterator.next();
            System.out.println(userlevel.getId() + " " + userlevel.getName());
        }         
         
        session.close();
        sessionFactory.close();
    }    
    //@Test
    public void userlevelshow() {       
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();
        
        Userlevelshow userlevelshow = null;
        List list = session.createQuery("from Userlevelshow").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            userlevelshow = (Userlevelshow) iterator.next();
            System.out.println(userlevelshow.getId() + " " + userlevelshow.getName());
        }         
         
        session.close();
        sessionFactory.close();
    }        
    //@Test
    public void user() {       
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();
        
        User user = null;
        //List list = session.createQuery("select COUNT(*) from User").list();
        List list = session.createQuery("from User").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            user = (User) iterator.next();
            System.out.println(user.getId() + " " + user.getName() + " " + user.getRegtime() + " " +user.getUserlevel());
        }         
         
        session.close();
        sessionFactory.close();
    }         
    //@Test
    public void userinfo() { 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();      
        
        Userinfo userinfo = null;
        List list = session.createQuery("from Userinfo").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            userinfo = (Userinfo) iterator.next();
            System.out.println(userinfo.getId() + " " 
                    + userinfo.getUser() + " " +userinfo.getUserlevelshow());
        }         
        
        session.close();
        sessionFactory.close();        
    }    
    //@Test
    public void usertemp() { 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();      
        
        Usertemp usertemp = null;
        List list = session.createQuery("from Usertemp").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            usertemp = (Usertemp) iterator.next();
            System.out.println(usertemp.getId() + " "+ usertemp.getUser());
        }         
        
        session.close();
        sessionFactory.close();        
    }    
    
    //@Test
    public void topicarea() { 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();      
        
        Topicarea topicarea = null;
        List list = session.createQuery("from Topicarea").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            topicarea = (Topicarea) iterator.next();
            System.out.println(topicarea.getId() + " "+ topicarea.getName());
        }         
        
        session.close();
        sessionFactory.close();        
    }   
    //@Test
    public void topictitle() { 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();      
        
        Topictitle topictitle = null;
        List list = session.createQuery("from Topictitle").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            topictitle = (Topictitle) iterator.next();
            //System.out.println(topictitle.getId() + " "+ topictitle.getTitle() + " " + topictitle.getTopicarea());
            //System.out.println(topictitle.getTopicinfo().size());
        }
        //Hibernate.initialize(Topictitle.class);
        //Hibernate.initialize(Topicarea.class);
        //System.out.println(topictitle.getTopicarea());
        
        session.close();

        sessionFactory.close();        
    }     
    //@Test
    public void topicinfo() { 
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();         
        Session session = sessionFactory.openSession();      
        
        Topicinfo topicinfo = null;
        List list = session.createQuery("from Topicinfo").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            topicinfo = (Topicinfo) iterator.next();
            System.out.println(topicinfo.getId() + " "+ topicinfo.getText() + " " 
                    + topicinfo.getTopictitle() + " " + topicinfo.getUser());
        }         
        
        session.close();
        sessionFactory.close();        
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
}










