/*
 * 悲观锁是从字段到事务的全粒度锁
 * 乐观锁则是字段的更新锁
 * 程序可悲观锁、乐观锁并存。
 */
package com.herograve.hbn.mytest;

import org.hibernate.LockMode;
import org.hibernate.Query;
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
public class Test_lock {

    private Configuration conf;
    private SessionFactory factory;

    public Test_lock() {
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

    /*
     * add Pessimistic Locking way:
     * Criteria.setLockMode
     * Query.setLockMode
     * Session.lock
     */
    @Test
    public void hello() {
        Session session = factory.openSession();
        Query query = session.createQuery("from CriteriaTest ct where ct.price > 100");
        query.setLockMode("ct", LockMode.UPGRADE);//Pessimistic Lock
        query.list();
    }

}