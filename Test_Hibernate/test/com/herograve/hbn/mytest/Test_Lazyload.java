/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.py.hib.relation.one2many.Child;

/**
 *
 * @author Administrator
 */
public class Test_Lazyload {

    private Configuration conf;
    private SessionFactory factory;

    public Test_Lazyload() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure();
        factory = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        factory.close();
    }

    @Test
    public void testLazyload() {
        Session session = factory.openSession();

        //when lazy=true, will error: the owning Session was closed
        //List results = session.createCriteria(Child.class).list();
        //session.close();
        //getProperty...

        List results = session.createCriteria(Child.class).createCriteria("father").list();
        //List results = factory.openSession().createCriteria(Child.class).createAlias("father","dad").list();
        session.close();

        Iterator i = results.iterator();
        while (i.hasNext()) {
            Child c = (Child) i.next();
            System.out.println(c.getFather().getName());
        }
    }

}