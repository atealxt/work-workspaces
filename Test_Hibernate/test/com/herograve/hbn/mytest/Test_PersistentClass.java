/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.mytest;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.py.hib.mytest.multipk.MultiPK;

/**
 *
 * @author Administrator
 */
public class Test_PersistentClass {

    private Configuration conf;
    private SessionFactory factory;

    public Test_PersistentClass() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(MultiPK.class);
        factory = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        factory.close();
    }

    @Test
    public void testPersistentClass() throws Exception {
        //get the table info(table,columns, and etc.)

        PersistentClass persistent = conf.getClassMapping(MultiPK.class.getName());
        if (persistent == null) {
            conf = conf.addClass(MultiPK.class);
            persistent = conf.getClassMapping(MultiPK.class.getName());
        }
        System.out.println(persistent.getTable());
        System.out.println(persistent.getTable().getPrimaryKey());
    }
}