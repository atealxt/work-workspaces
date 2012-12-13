package pdms.platform.core;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pdms.platform.configeration.DaoManager;
import static pdms.platform.constant.CommonConstant.*;

/**
 * Dao模板，不应该直接使用。
 * @author LUSuo(atealxt@gmail.com)
 */
public abstract class EasyDao {

    private static Log logger = LogFactory.getLog(EasyDao.class);
    /** 为了session间同步而设置的信息池。*/
    private List<Class> updatingPool = new ArrayList<Class>(0);

    /**
     * 此函数能反应是否正在对DB进行更新。
     * 使用它可以保证多线程下对session缓存的数据正确性。
     * 例如：if(isUpdating(User.class)) Thread.sleep(10);
     * @param clazz 所要判断的类
     * @return True 正在处理该类 False 没有在处理该类
     */
    public boolean isUpdating(Class clazz) {
        if (updatingPool.contains(clazz)) {
            return true;
        }
        return false;
    }

    /** 指定执行SQL */
    public List findAll(int maxNum, int startNum, String querySQL) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery(querySQL);
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }
        return query.list();
    }

    public List Select(String SQL) {
        Session session = DaoManager.getSession();
        session.beginTransaction();
        List list = session.createQuery(SQL).list();
        return list;
    }

    public Object SelectUnique(String SQL) {
        Session session = DaoManager.getSession();
        session.beginTransaction();
        return session.createQuery(SQL).uniqueResult();
    }

    public Object SelectFirst(String SQL) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 返回总数据数<br>
     * select count(*)
     */
    public int Count(String pojoName) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("select count(*) from " + pojoName);
        Long l = (Long) query.iterate().next();
        return l.intValue();
    }

    /**
     * @return 0:正常 1：异常
     */
    public int Insert(Object obj) {
        Session session = DaoManager.getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.save(obj);
            tran.commit();
            return DB_STATUS_OK;
        } catch (Exception ex) {
            logger.error(ex);
            return DB_STATUS_NG;
        }
    }

    /**
     * @return 0:正常 1:异常 2:警告
     */
    public int Update(Object obj) {
        int iRet;
        updatingPool.add(obj.getClass());

        Session session = DaoManager.getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.update(obj);
            tran.commit();
            iRet = DB_STATUS_OK;
        } catch (NonUniqueObjectException ex) {
            try {
                session.merge(obj);
                tran.commit();
                iRet = DB_STATUS_WARNING;
            } catch (Exception e) {
                tran.rollback();
                logger.error(e);
                iRet = DB_STATUS_NG;
            }
        } catch (Exception ex) {
            tran.rollback();
            logger.error(ex);
            iRet = DB_STATUS_NG;
        }

        updatingPool.remove(obj.getClass());
        return iRet;
    }

    /**
     * @return 0:正常 1:异常 2:警告
     */
    public int Delete(Object obj) {
        Session session = DaoManager.getSession();
        Transaction tran = session.beginTransaction();
        try {
            session.delete(obj);
            tran.commit();
            return DB_STATUS_OK;
        } catch (NonUniqueObjectException ex) {
            try {
                session.clear();
                session.delete(obj);
                tran.commit();
                return DB_STATUS_WARNING;
            } catch (Exception e) {
                logger.error(e);
                return DB_STATUS_NG;
            }
        } catch (Exception ex) {
            logger.error(ex);
            return DB_STATUS_NG;
        }
    }

//    public int Execute(String SQL) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
}
