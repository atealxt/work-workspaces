package pdms.components.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.RoleDao;
import pdms.components.pojo.Role;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class RoleDaoImpl extends EasyDao implements RoleDao {

    @Override
    public List<Role> selectAll() {
        return super.Select("from Role order by id");
    }

    @Override
    public Role findByName(String name) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Role where name=:name order by id");
        query.setParameter("name", name);

        return (Role) query.uniqueResult();
    }
}
