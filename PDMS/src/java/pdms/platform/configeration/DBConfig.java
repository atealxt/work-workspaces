package pdms.platform.configeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class DBConfig {

    private static Log logger = LogFactory.getLog(DBConfig.class);
    private static Configuration conf;
    private static SessionFactory sessionFactory;

    public void init() {
        logger.info("SessionFactory init");
        conf = new Configuration().configure();
        sessionFactory = conf.buildSessionFactory();
    }

    public void destroy() {
        logger.info("SessionFactory destroy");
        sessionFactory.close();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected SessionFactory addClass(Class clazz) {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
        conf.addClass(clazz);
        sessionFactory = conf.buildSessionFactory();
        return sessionFactory;
    }
}
