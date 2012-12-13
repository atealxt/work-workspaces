/*
 * 如果写好了java文件和hbm文件，就可以用SchemaExport直接生成DDL文件
 */
package com.herograve.hbn.autocreatecode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class Test_SchemaExport {

    private Configuration conf;
    private SessionFactory sf;

    public Test_SchemaExport() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure().addClass(Topictype.class).addClass(Topic.class);
        sf = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        sf.close();
    }

    @Test
    public void createTable() {
        SchemaExport export = new SchemaExport(conf);
        export.setOutputFile("E:/Data/1201/a.sql");
        //export.create(true, false);
        export.create(true, true);
    }

    @Test
    public void addTopictype() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Topictype tt = new Topictype();
        tt.setName("type A");
        sess.save(tt);

        Topictype tt2 = new Topictype();
        tt2.setName("type B");
        sess.save(tt2);

        Topictype tt3 = new Topictype();
        tt3.setName("type C");
        sess.save(tt3);

        trans.commit();
    }

    /*
     * 有文章栏目和文章两个表，两个表之间既有多对多关系，又有一对多关系
     * 每篇文章都保存一个文章栏目的id，如果发到多个栏目的文章就放到多对多的中间表中，这样中间表的负荷就没那么重。
     * (这只是一种做法，具体好不好值得商榷。)
     */
    @Test
    public void testUnit() {

        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();
        List list = sess.createQuery("from Topictype").list();

        //一对多
        Topic topic = new Topic();
        topic.setInfo("aaa");
        topic.setType((Topictype) list.get(0));
        sess.save(topic);

        //多对多
        Topic topic2 = new Topic();
        topic2.setInfo("bbb");
        Set<Topictype> set = new HashSet<Topictype>();
        set.add((Topictype) list.get(0));
        set.add((Topictype) list.get(1));
        topic2.setTypes(set);
        sess.save(topic2);

        trans.commit();
    }
}