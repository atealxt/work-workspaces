/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.py.hib.relation.one2one.Card;
import org.py.hib.relation.one2one.Person;

/**
 *
 * @author Administrator
 */
public class Test_projection {

    private Configuration conf;
    private SessionFactory factory;

    public Test_projection() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(Person.class).addClass(Card.class);
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

        DetachedCriteria dCriteria = DetachedCriteria.forClass(Person.class);
        //dCriteria.add(Restrictions.like("name", "p%"));

        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.count("name"));

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = dCriteria.getExecutableCriteria(session);
        criteria.setProjection(projectionList);
        List results = criteria.list();

        Object obj = results.get(0);
        System.out.println(obj);
    }
}