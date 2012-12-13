/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.py.hib.quickstart.User;
import org.py.hib.relation.one2many.Child;
import org.py.hib.relation.one2many.Father;

/**
 *
 * @author Administrator
 */
public class Test_HQL {

    private Configuration conf;
    private SessionFactory factory;

    public Test_HQL() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(Child.class).addClass(Father.class);
        factory = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        factory.close();
    }

    //@Test
    public void inner_join_fetch() {

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List list = session.createQuery("from Father f inner join fetch f.children").list();
        System.out.println(list);//Father
        List list2 = session.createQuery("from Father f inner join f.children").list();
        System.out.println(list2);//Object.
    }

    //@Test
    public void left_outer_join_fetch() {

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        //get Father at first,then get children.lazy loading.good.
        List list = session.createQuery("from Father f").list();
        Father father = (Father) list.get(0);
        System.out.println(father.getChildren());

        //get Father and children at the same time.
        List list2 = session.createQuery("from Father f left outer join fetch f.children").list();
        System.out.println(list2);//Father.
        List list3 = session.createQuery("from Father f left outer join f.children").list();
        System.out.println(list3);//Object.
    }

    //@Test
    public void right_outer_join() {

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        //cannnot add fetch
        List list = session.createQuery("from Father f right outer join f.children").list();
        System.out.println(list);
    }

    //@Test
    public void subquery() {

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List list = session.createQuery("from Father f where (select count(*) from f.children)> 0").list();
        System.out.println(list);
    }
}