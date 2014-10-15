package com.hg.core.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class JdoManager {

    private static Log logger = LogFactory.getLog(JdoManager.class);

    private static final ThreadLocal<PersistenceManager> session = new ThreadLocal<PersistenceManager>();

    private static final PersistenceManagerFactory pmfInstance = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    private JdoManager() {}

    public static PersistenceManager getSession() {
        PersistenceManager sess = session.get();
        if (sess == null || sess.isClosed()) {
            logger.trace("create new session");
            sess = pmfInstance.getPersistenceManager();
            session.set(sess);
        } else {
            logger.trace("return existent session");
        }
        return sess;
    }

    public static void closeSession() {
        final PersistenceManager sess = session.get();
        if (sess != null) {
            logger.trace("close session");
            session.set(null);
            if (!sess.isClosed()) {
                sess.close();
            }
        }
    }
}
