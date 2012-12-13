package pdms.components.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.GroupDao;
import pdms.components.pojo.Group;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class GroupDaoImpl extends EasyDao implements GroupDao {

    @Override
    public List<Group> selectAll() {
        return super.Select("from Group order by id");
    }

    @Override
    public Group findByName(String name) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Group where name=:name order by id");
        query.setParameter("name", name);

        return (Group) query.uniqueResult();
    }

    @Override
    public List<Group> findAll(int maxNum, int startNum) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Group order by id");
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }
        return query.list();
    }

    @Override
    public int Insert(Group obj) {
        return super.Insert(obj);
    }

    @Override
    public int Update(Group obj) {
        return super.Update(obj);
    }

    @Override
    public Group findById(int id) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Group where id=:id order by id");
        query.setParameter("id", id);

        return (Group) query.uniqueResult();
    }

    @Override
    public int Delete(Group obj) {
        return super.Delete(obj);
    }
}
