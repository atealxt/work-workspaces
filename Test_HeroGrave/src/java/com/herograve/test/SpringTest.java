/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.test;

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
public class SpringTest {

    private static SessionFactory factory;  
    private static Configuration conf;
    private List list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
    
    public static void setUp() {
        try{
            conf = new Configuration().configure();
            factory = conf.buildSessionFactory();         
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void tearDown() {
        factory.close();         
    }   
    
    public void search() {
        setUp();
        
        Session session = null;  
        HibernateTest ht=null;        
        session = factory.openSession();  
        
        list = session.createQuery("from HibernateTest").list();
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            ht = (HibernateTest) iterator.next();
            System.out.println(ht.getId() + " " + ht.getName());
        }     
        
        session.close();          
        tearDown();
        System.out.println("hibernate set seccess!");
    }
    
    public void insert(String str){
        setUp();
        
        Session session = factory.openSession();
        Transaction ts = session.beginTransaction();
        HibernateTest ht = new HibernateTest();
        ht.setName(str);
        session.save(ht);
        ts.commit();

        session.close();        
        tearDown();
    }
    
    public static void main(String args[]){
        new SpringTest().search();
    }
    
}









