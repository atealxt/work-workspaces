package com.herograve.hbn.mytest;

import com.herograve.hbn.util.MyInterceptor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class Test_Interceptor {

    private Configuration conf;
    private SessionFactory factory;

    public Test_Interceptor() {
    }

    @Before
    public void setUp() {

        conf = new Configuration().addClass(com.herograve.hbn.mytest.Test.class).
            setInterceptor(new MyInterceptor()).configure();
        factory = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        factory.close();
    }

    @Test
    public void hello() throws SQLException {

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        //test1 start
        //way1
        //PreparedStatement ps = session.connection().prepareStatement("SELECT * FROM Test WHERE TEST_CHAR = 'a'");
        //way2
        PreparedStatement ps = session.connection().prepareStatement("SELECT * FROM Test WHERE TEST_CHAR = RPAD(?, 10)");
        ps.setString(1, "a");
        ResultSet rs = ps.executeQuery();
        System.out.println(rs.next());
        rs.close();
        ps.close();
        //test1 end

        //test2 start
        //not use RTRIM also successful in hibernate,but in s2dao is fail!
        //List list = session.createQuery("FROM Test WHERE TEST_CHAR = 'a'").list();
        List list = session.createQuery("FROM Test WHERE RTRIM(TEST_CHAR) = 'a'").list();
        session.close();
        assertTrue(list.size() > 0);
    //test2 end

    }
}