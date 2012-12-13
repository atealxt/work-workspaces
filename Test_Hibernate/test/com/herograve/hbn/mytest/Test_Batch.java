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
    //������ͨ������Ҫ���ݻ��棬������Ὣ�ڴ�ľ�����������GC����������ڴ����ޣ����������������ԡ�
    //���ǽ���������Ƕ���������С�
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