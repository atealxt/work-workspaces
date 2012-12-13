/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.py.hib.mytest.generator.GeneratorTest;

/**
 *
 * @author Administrator
 */
public class Test_PKGenerator {

    private Configuration conf;
    private SessionFactory factory;

    public Test_PKGenerator() {
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
    public void testGenerator() throws Exception {
        Session session = factory.openSession();

        Transaction tran = session.beginTransaction();
        GeneratorTest gt = new GeneratorTest();
        gt.setName("ok!");
        session.save(gt);
        tran.commit();

        session.close();
    }
}