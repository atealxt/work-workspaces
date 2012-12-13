package sshfile.dao;

import sshfile.model.*;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import java.util.List;

public class TfileDAOHibernate
    extends HibernateDaoSupport implements TfileDAO
{
    public Tfile findByFildId(String fileId)
    {
        return (Tfile) getHibernateTemplate().get(Tfile.class, fileId);
    }
    public void save(Tfile tfile)
    {
        getHibernateTemplate().save(tfile);
        getHibernateTemplate().flush();
    }
    public List findAll()
    {
       return getHibernateTemplate().loadAll(Tfile.class);
    }
}
