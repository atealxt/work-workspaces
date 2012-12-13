/*
 * Table per subclass(每个子类一张表，适合子表属性多的情况。如果子表属性少，使用Table per class把整体冗余在一张表里更合适)
 *
 * PS:
 * 数据库的面向对象类继承设计，最好只应用在先设计业务/Java后设计DB的开发模式下。
 */
package com.herograve.hbn.joinedsubclass;

import com.herograve.hbn.subclass.TBook;
import com.herograve.hbn.subclass.TDvd;
import com.herograve.hbn.subclass.TItem;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class Test_joinedsubclass {

    private Configuration conf;
    private SessionFactory sf;

    public Test_joinedsubclass() {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(TItem.class);
        sf = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        sf.close();
    }

    //@Test
    public void createTable() {
        SchemaExport export = new SchemaExport(conf);
        export.setOutputFile("E:/Data/1201/a.sql");
        //export.create(true, false);
        export.create(true, true);
    }

    //@Test
    public void testInsert() {

        TBook book = new TBook();
        book.setName("bookA");
        book.setManufacturer("Wiley");
        book.setPageCount(100);

        TDvd dvd = new TDvd();
        dvd.setName("dvdA");
        dvd.setManufacturer("Colombia");
        dvd.setRegionCode("6");

        Session sess = sf.getCurrentSession();
        Transaction tran = sess.beginTransaction();
        sess.save(book);
        sess.save(dvd);
        tran.commit();

    }

    @Test
    public void testSearch() {
        Session sess = sf.openSession();
        //List list = sess.createQuery("from TBook").list();
        List list = sess.createQuery("from TItem").list();
    }
}