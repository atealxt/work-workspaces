package org.py.hib.quickstart;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import org.py.hib.mytest.list.UploadUser;
import org.py.hib.relation.many2many.*;
import org.py.hib.relation.one2many.*;
import org.py.hib.relation.one2one.*;

public class QuickStartTest {

    private SessionFactory factory;
    private Configuration conf;
    private String m_name = "name!";
    private String m_name2 = "name2!";

    public QuickStartTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        conf = new Configuration().addClass(User.class).addClass(Person.class).addClass(Father.class).
            addClass(Child.class).addClass(Student.class).addClass(Card.class).addClass(Teacher.class).configure();
        factory = conf.buildSessionFactory();

    }

    @After
    public void tearDown() throws Exception {
        factory.close();
    }

    @Test
    public void hello() {
        User u = new User();
        u.setId("1");

        assertTrue("success!", "1".equals(u.getId()));
    }

    //@Test
    public void testSave() throws Exception {
        System.out.println("\n=== test save ===");
        User u = new User();
        u.setName(m_name); // test username = m_name

        Session session = null;
        Transaction tran = null;
        try {
            session = factory.openSession();
            tran = session.beginTransaction();
            session.save(u);
            tran.commit();

            //Assert.assertEquals(u.getId() != null, true);
            assertTrue("success!", u.getId() != null);
        //System.out.println(u.getId());
        } catch (Exception ex) {
            tran.rollback();
            throw ex;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } finally {
                    if (session != null) {
                        session = null;
                    }
                }
            }
        }
    }

    @Test
    public void testFind() throws Exception {
        System.out.println("\n=== test find ===");
        Session session = null;
        try {
            session = factory.openSession();

            User u = null;
            //Iterator iterator = session.createQuery("from User where id='1' ").iterate();
            List list = session.createQuery("from User").list();
            //Iterator iterator = session.createQuery("from User").iterate();
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                u = (User) iterator.next();
                System.out.println(u.getId() + " " + u.getName());
            }
        //User u = (User) session.createQuery("from User").list().get(0);

        //Assert.assertEquals(true, u.getId() != null);
        //Assert.assertEquals(m_name, u.getName());
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } finally {
                    if (session != null) {
                        session = null;
                    }
                }
            }
        }
    }

    //@Test
    public void testModify() throws Exception {
        System.out.println("\n=== test modify ===");
        Session session = null;
        Transaction tran = null;
        try {
            session = factory.openSession();
            tran = session.beginTransaction();

            User u = (User) session.createQuery("from User").list().get(0);
            u.setName(m_name2);  // update username = m_name2.（original username = m_name）
            tran.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } finally {
                    if (session != null) {
                        session = null;
                    }
                }
            }
        }

        //update then select
        testFind();
    }

    //@Test
    public void testDelete() throws Exception {
        System.out.println("\n=== test delete ===");
        Session session = null;
        Transaction tran = null;
        try {
            session = factory.openSession();
            tran = session.beginTransaction();

            User u = (User) session.createQuery("from User").list().get(0);
            session.delete(u);
            tran.commit();

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } finally {
                    if (session != null) {
                        session = null;
                    }
                }
            }
        }

        /*
         * delete then select
         */
        System.out.println("\n=== test find after delete ===");
        try {
            session = factory.openSession();
            Integer num = (Integer) session.createQuery("from User").list().size();

            Assert.assertEquals(0, num.intValue());
        //System.out.println(num);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (session != null) {
                try {
                    session.close();
                } finally {
                    if (session != null) {
                        session = null;
                    }
                }
            }
        }
    }

    @Test
    public void testOne2One() throws Exception {
        Session session = factory.openSession();

        Card card = null;
        Person person = null;

        /*
        List listCard = session.createQuery("from Card").list();
        Iterator iteratorCard = listCard.iterator();
        while(iteratorCard.hasNext()){
        card = (Card)iteratorCard.next();
        System.out.println(card.getId() + " " + card.getCardDesc());
        }
         */

        List listPerson = session.createQuery("from Person").list();
        Iterator iteratorPerson = listPerson.iterator();
        while (iteratorPerson.hasNext()) {
            person = (Person) iteratorPerson.next();
            System.out.println(person.getId() + " " + person.getName() + " " + person.getCard());
        }

        /*
        List listPerson = session.createQuery("from Person where card_id='1'").list();
        Iterator iteratorPerson = listPerson.iterator();
        while(iteratorPerson.hasNext()){
        person = (Person)iteratorPerson.next();
        }
        Transaction tran = session.beginTransaction();
        person.setName("My name changed!");
        tran.commit();
         */

        session.close();
    }

    @Test
    public void testOne2Many() throws Exception {
        Session session = factory.openSession();

        Father father = null;
        //Child child = null;

        List list = session.createQuery("from Father").list();
//        Iterator iterator = list.iterator();
//        while(iterator.hasNext()){
//            father=(Father)iterator.next();
//            System.out.println(father.getId()+" "+father.getName()+" "+father.getChildren());
//        }
        /*
        child.setName("My name changed!");
        Transaction tran = session.beginTransaction();
        tran.commit();
         */

        session.close();
    }

    //@Test
    public void testMany2Many() throws Exception {
        Session session = factory.openSession();

        Student student = null;
        Teacher teacher = null;
        Teacher teacherDelete = null;

        List list = session.createQuery("from Student where name='student1'").list();
        student = (Student) list.get(0);

        Set<Teacher> setTeachers = student.getTeachers();
        Iterator iterator = setTeachers.iterator();
        while (iterator.hasNext()) {
            teacher = (Teacher) iterator.next();
            if (teacher.getName().equals("teacher2")) {
                teacherDelete = teacher;
            }
        }
        setTeachers.remove(teacherDelete);

        teacher.setName("teacher name changed!");
        Transaction tran = session.beginTransaction();
        try {
            tran.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        }

        session.close();
    }


    //@Test
    public void testList() throws Exception {
        Session session = factory.openSession();

        UploadUser uploadUser1 = new UploadUser();
        uploadUser1.setName("aaa");
        uploadUser1.addFileName("a.txt");
        uploadUser1.addFileName("a.sql");
        UploadUser uploadUser2 = new UploadUser();
        uploadUser2.setName("bbb");
        uploadUser2.addFileName("b.txt");
        uploadUser2.addFileName("b.txt");
        uploadUser2.addFileName("b.sql");

        session.save(uploadUser1);
        session.save(uploadUser2);

        Transaction tran = session.beginTransaction();
        try {
            tran.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tran.rollback();
        }

        session.close();
    }
}
