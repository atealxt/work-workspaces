package pdms.components.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.IdentityDao;
import pdms.components.pojo.Identity;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class IdentityDaoImpl extends EasyDao implements IdentityDao {

    @Override
    public List<Identity> selectAll() {
        return super.Select("from Identity order by id");
    }

    @Override
    public Identity findByName(String name) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Identity where name=:name order by id");
        query.setParameter("name", name);

        return (Identity) query.uniqueResult();
    }
}
