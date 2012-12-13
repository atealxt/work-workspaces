/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.py.hib.quickstart.User;

/**
 *
 * @author Administrator
 */
public class Test_Statistics {

    private Configuration conf;
    private SessionFactory factory;

    public Test_Statistics() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(User.class);
        factory = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        factory.close();
    }

    @Test
    public void testStatistics() throws Exception {
        Statistics stats = factory.getStatistics();
        stats.setStatisticsEnabled(true);

        Session session = factory.openSession();
        session.createQuery("from User u").list();

        System.out.println(stats.getSessionOpenCount());
        stats.logSummary();

        EntityStatistics itemStats = stats.getEntityStatistics("auction.model.Item");
        System.out.println(itemStats.getFetchCount());
    }
}