package com.herograve.hbn.mytest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test_SessionLive {

    private SessionFactory factory;
    private Configuration conf;

    public Test_SessionLive() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure();
        factory = conf.buildSessionFactory();

    }

    @After
    public void tearDown() throws Exception {
        factory.close();
    }

    @Test
    public void testSessionLive() throws Exception {

        /*
         * getCurrentSession:return currentSessionContext.currentSession();
         * CurrentSessionContext:return new ***SessionContext( this );
         */

        Session sessionOne = factory.getCurrentSession();
        sessionOne.beginTransaction();
        System.out.println("session1: " + sessionOne.getFlushMode());

        Session sessionTwo = factory.getCurrentSession();
        sessionTwo.beginTransaction();//important.
        System.out.println("session2: " + sessionTwo.getFlushMode());

        //notice! result:false
        System.out.println("sessionOne equals sessionTwo? " + (sessionOne.equals(sessionTwo)));

        sessionOne.setFlushMode(FlushMode.ALWAYS);
        System.out.println("session1: " + sessionOne.getFlushMode());
        System.out.println("session2: " + sessionTwo.getFlushMode());



    //not need.
    //sessionOne.close();
    //sessionTwo.close();
    }
}














