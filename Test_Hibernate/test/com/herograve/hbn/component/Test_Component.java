/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.herograve.hbn.component;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class Test_Component {

    private Configuration conf;
    private SessionFactory sf;

    public Test_Component() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(TUser.class);
        sf = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        sf.close();
    }

    @Test
    public void testGetId() {
        SchemaExport export = new SchemaExport(conf);
        export.setOutputFile("E:/Data/1201/a.sql");
        export.create(true, false);
    }
}