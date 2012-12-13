package pdms.components.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pdms.components.dao.FileDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.UploadFile;
import pdms.components.pojo.User;
import pdms.platform.configeration.DIManager;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class FileDaoImpl extends EasyDao implements FileDao {

    @Override
    public List<UploadFile> find(int maxNum, int startNum) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from UploadFile where del=false order by uploadDate desc,id asc");
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }

        List<UploadFile> ret = query.list();
        return ret;
    }

    @Override
    public List<UploadFile> findByUserLoginId(String loginId, int maxNum, int startNum) {

        UserDao userDao = (UserDao) DIManager.getBean("UserDao");
        User user = userDao.findByLoginId(loginId);
        if (user == null) {
            return new ArrayList<UploadFile>(0);
        }
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from UploadFile where user=:user and del=false order by uploadDate desc,id asc");
        query.setParameter("user", user);
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }

        List<UploadFile> ret = query.list();
        return ret;
    }

    @Override
    public List<UploadFile> findAllByUserLoginId(String loginId) {
        return findByUserLoginId(loginId, -1, 0);
    }

    @Override
    public UploadFile findById(String id) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from UploadFile where id=:id and del=false order by id");
        query.setParameter("id", id);

        return (UploadFile) query.uniqueResult();
    }

    @Override
    public String delFile(String fileId) {
        UploadFile f = findById(fileId);
        if (f == null) {
            return null;
        }
        String ret = f.getAddress();
        Session sess = DaoManager.getSession();
        Transaction trans = sess.beginTransaction();
        sess.delete(f);
        trans.commit();
        return ret;
    }

    @Override
    public int Insert(UploadFile obj) {
        return super.Insert(obj);
    }

    @Override
    public int Update(UploadFile obj) {
        return super.Update(obj);
    }
}
