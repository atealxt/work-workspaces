package pdms.platform.configeration;

//import java.util.ArrayList;
//import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class DaoManager {

    private static SessionFactory sessionFactory;
    private static final ThreadLocal<Session> session = new ThreadLocal();
//    private static final ThreadLocal<List<Session>> session = new ThreadLocal();

    private DaoManager() {
    }


//    static {
//        DBConfig dbConfig = (DBConfig) DIManager.getBean("DBConfig");
//        sessionFactory = dbConfig.getSessionFactory();
//    }
    private static void init() {
        DBConfig dbConfig = (DBConfig) DIManager.getBean("DBConfig");
        sessionFactory = dbConfig.getSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        final SessionFactory sf = sessionFactory;
        return sf;
    }

    public static Session getSession() {

        if (sessionFactory == null) {
            init();
        }

        Session sess = session.get();
        if (sess == null || !sess.isOpen()) {
            sess = sessionFactory.getCurrentSession();
            session.set(sess);
//            System.out.println("create new sess: " + Thread.currentThread().getName());
        } /*else if (sess.isOpen()) {
        System.out.println("sess is still opend: " + Thread.currentThread().getName());
        }*/
//        List<Session> sessList = session.get();
//        if (sessList == null) {
//            sessList = new ArrayList<Session>(1);
//        }
//        Session sess = sessionFactory.getCurrentSession();
//        sessList.add(sess);
//        session.set(sessList);

        return sess;
    }

    public static void closeSession() {
        Session sess = session.get();
        session.set(null);
        if (sess != null && sess.isOpen()) {
            sess.close();
//            System.out.println("close sess: " + Thread.currentThread().getName());
        }  /*else if (sess == null) {
    System.out.println("sess is null: " + Thread.currentThread().getName());
    } else {
    System.out.println("sess is already closed: " + Thread.currentThread().getName());
    }*/
//        List<Session> sessList = session.get();
//        session.set(null);
//        if (sessList != null && sessList.size() > 0) {
//            for (Session s : sessList) {
//                if (s != null && s.isOpen()) {
//                    s.close();
//                }
//            }
//        }
    }
}
