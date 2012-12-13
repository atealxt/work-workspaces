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
import org.py.hib.quickstart.User;

/**
 *
 * @author Administrator
 */
public class Test_Batch {

    private Configuration conf;
    private SessionFactory factory;

    public Test_Batch() {
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
    //批处理通常不需要数据缓存，否则你会将内存耗尽并大量增加GC开销。如果内存有限，那这种情况会很明显。
    //总是将批量插入嵌套在事务中。
    public void testBatch() throws Exception {
        Session session = factory.openSession();

        Transaction tr = session.beginTransaction();
        for (int i = 1; i <= 8; i++) {
            User user = new User();
            //user.setId( (2+i)+"" );
            user.setName(i + "");
            session.setCacheMode(CacheMode.IGNORE);
            session.save(user);

            if (i % 4 == 0) {
                session.flush();
                session.clear();
            }
        }
        tr.commit();

        session.close();

    }
}