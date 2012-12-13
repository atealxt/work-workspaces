/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.py.hib.mytest.criteria.CriteriaTest;

/**
 *
 * @author Administrator
 */
public class Test_Lifecycle {

    private Configuration conf;
    private SessionFactory factory;

    public Test_Lifecycle() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(CriteriaTest.class);
        factory = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        factory.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {

        Session session = factory.openSession();

        //Transient
        CriteriaTest criteria = new CriteriaTest();
        criteria.setName("abc");
        criteria.setPrice(10);
        System.out.println("Transient: " + criteria.getId());

        //Persistent
        session.save(criteria);
        System.out.println("Persistent: " + criteria.getId());
        session.flush();//flush to session cache
        System.out.println(session.createQuery("from CriteriaTest where name='abc'").list().get(0));//get data from cache.

        //Detached
        session.close();
        System.out.println("Detached: " + criteria.getId());

        //Repersistent
        Session session2 = factory.openSession();
        criteria.setName("cba");
        session2.update(criteria);
        System.out.println("Repersistent: " + criteria.getId());//may be is dirty data,notice!!
        session2.flush();//flush to session cache

        session2.close();
    }
}