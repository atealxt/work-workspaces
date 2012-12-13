/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import com.herograve.hbn.util.CriteriaUtils;
import com.herograve.hbn.util.RestrictionsType;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.py.hib.mytest.criteria.CriteriaTest;
import org.py.hib.relation.one2many.Father;

/**
 *
 * @author Administrator
 */
public class Test_Criteria {

    private Configuration conf;
    private SessionFactory factory;

    public Test_Criteria() {
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
    public void testCriteria() throws Exception {

        //way1
        Session sessionOne = factory.getCurrentSession();
        sessionOne.beginTransaction();
        Criteria criteria1 = sessionOne.createCriteria(CriteriaTest.class).add(Restrictions.eq("price", 100));
        List items1 = criteria1.list();
        Iterator iterator1 = items1.iterator();
        while (iterator1.hasNext()) {
            CriteriaTest ct = (CriteriaTest) iterator1.next();
            System.out.println(ct.getId() + ct.getName() + ct.getPrice());
        }

        //way2
        Session sessionTwo = factory.getCurrentSession();
        sessionTwo.beginTransaction();
        DetachedCriteria query = DetachedCriteria.forClass(CriteriaTest.class);
        query.add(Restrictions.like("name", "T", MatchMode.ANYWHERE));
        query.add(Restrictions.gt("price", 100));
        Criteria criteria2 = query.getExecutableCriteria(sessionTwo);
        List items2 = criteria2.list();
        Iterator iterator2 = items2.iterator();
        while (iterator2.hasNext()) {
            CriteriaTest ct = (CriteriaTest) iterator2.next();
            System.out.println(ct.getId() + ct.getName() + ct.getPrice());
        }
    }

    @Test
    public void qbdcUtil() throws ClassNotFoundException, Exception {
        Map<RestrictionsType, Object[]> restrictions = new HashMap<RestrictionsType, Object[]>();

//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("age", 30);
//        map.put("id", "3");
//        restrictions.put(RestrictionsType.allEq, new Object[]{map});

        //restrictions.put(RestrictionsType.between, new Object[]{"age", 35, 45});
        //restrictions.put(RestrictionsType.eq, new Object[]{"name", "WE.TeD"});
        //restrictions.put(RestrictionsType.ge, new Object[]{"age", 40});
        //restrictions.put(RestrictionsType.gt, new Object[]{"age", 40});
        //restrictions.put(RestrictionsType.idEq, new Object[]{"1"});
        //restrictions.put(RestrictionsType.ilike, new Object[]{"name", "k", MatchMode.ANYWHERE});
        //restrictions.put(RestrictionsType.le, new Object[]{"age", 40});
        //restrictions.put(RestrictionsType.like, new Object[]{"name", "k", MatchMode.ANYWHERE});

        DetachedCriteria dCriteria = CriteriaUtils.getDetachedCriteria("org.py.hib.relation.one2many.Father", restrictions);
        Session session = factory.openSession();
        List results = dCriteria.getExecutableCriteria(session).list();

        Iterator i = results.iterator();
        while (i.hasNext()) {
            Father f = (Father) i.next();
            System.out.println(f.getId() + f.getName() + f.getAge());
        }
        session.close();
    }
}