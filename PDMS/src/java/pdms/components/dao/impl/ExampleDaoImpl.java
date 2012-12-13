package pdms.components.dao.impl;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.ExampleDao;
import pdms.components.pojo.Example;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class ExampleDaoImpl extends EasyDao implements ExampleDao {

    @Override
    public Example findByName(String name) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();

        Query query = sess.createQuery("from Example where name=:name");
        query.setParameter("name", name);
        Example e = (Example) query.uniqueResult();
        return e;
    }

    @Override
    public List<Example> getExamples() {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Example");
        return query.list();
    }
}
