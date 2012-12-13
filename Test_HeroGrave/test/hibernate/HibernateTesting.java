/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate;

import com.herograve.test.*;
import com.herograve.test.HibernateTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Administrator
 */
public class HibernateTesting {

    private static SessionFactory factory;  
    private static Configuration conf;     
    
    public HibernateTesting() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure();                 
        factory = conf.buildSessionFactory();   
        System.out.println("setUp");
    }

    @After
    public void tearDown() {
        factory.close(); 
        System.out.println("tearDown");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    //@Test
    public void search() {
        Session session = null;  
        HibernateTest ht=null;        
        session = factory.openSession();  
        
        List list = session.createQuery("from HibernateTest").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            ht = (HibernateTest) iterator.next();
            System.out.println(ht.getId() + " " + ht.getName());
        }     
        
        session.close();          
    }
    
    //@Test
    public void insert() {
        Session session = factory.openSession();  
        Transaction ts = session.beginTransaction();
        
        HibernateTest ht=new HibernateTest();        
        //ht.setId("3");
        ht.setName("asd");
        session.save(ht);
        ts.commit();
        
        session.close(); 
        search();
    }    
    

}












