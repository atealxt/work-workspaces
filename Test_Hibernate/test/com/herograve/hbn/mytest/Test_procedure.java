/* 
 * 3.2.3 can only support Using stored procedures for "querying"!
 */
package com.herograve.hbn.mytest;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class Test_procedure {

    private Configuration conf;
    private SessionFactory factory;

    public Test_procedure() {
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
    public void hello() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        //test1
        SQLQuery query = session.createSQLQuery("{Call p(?,?)}");
        query.setString(0, "id!!");
        query.setString(1, "name!!");
        List list = query.list();
        System.out.println(list);

//        session.flush();
//        session.close();

        //test2 jdbc style:
//        try {
//            CallableStatement call = session.connection().prepareCall("{CALL p(?,?)}");
//            call.setString(1, "id!!");
//            call.setString(2, "name!!");
//            call.execute();
//            session.flush();
//            call.close();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Test_procedure.class.getName()).log(Level.SEVERE, null, ex);
//        }


    }
}