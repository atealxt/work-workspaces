package pdms.components.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Project;
import pdms.components.pojo.User;
import pdms.platform.configeration.DIManager;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class ProjectDaoImpl extends EasyDao implements ProjectDao {

    @Override
    public Project findById(int id) {
        StringBuffer sb = new StringBuffer();
        sb.append("from Project where id=");
        sb.append(id);
        return (Project) super.SelectUnique(sb.toString());
    }

    @Override
    public Project findByName(String name) {
        StringBuffer sb = new StringBuffer();
        sb.append("from Project where name='");
        sb.append(name);
        sb.append("'");
        return (Project) super.SelectUnique(sb.toString());
    }

    @Override
    public List<Project> findByUserLoginId(String loginId) {
        UserDao userDao = (UserDao) DIManager.getBean("UserDao");
        User user = userDao.findByLoginId(loginId);
        if (user == null) {
            return new ArrayList<Project>(0);
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
//        Query query = sess.createQuery("from UserProjectRel where user=:user order by id");
//        query.setParameter("user", user);
//
//        List<UserProjectRel> uprs = query.list();
//        if (uprs == null || uprs.size() == 0) {
//            return new ArrayList<Project>(0);
//        }
//        List<Project> ret = new ArrayList<Project>();
//        for (UserProjectRel upr : uprs) {
//            ret.add(upr.getProject());
//        }
//        return ret;
        Query query = sess.createQuery("select upr.project from UserProjectRel as upr where upr.user=:user order by upr.project.id");
        query.setParameter("user", user);
        return query.list();
    }

    @Override
    public List<Project> findByManagerLoginId(String loginId) {
        UserDao userDao = (UserDao) DIManager.getBean("UserDao");
        User user = userDao.findByLoginId(loginId);
        if (user == null) {
            return new ArrayList<Project>(0);
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
//        Query query = sess.createQuery("from UserProjectRel where user=:user and canManage=true order by id");
//        query.setParameter("user", user);
//
//        List<UserProjectRel> uprs = query.list();
//        if (uprs == null || uprs.size() == 0) {
//            return new ArrayList<Project>(0);
//        }
//        List<Project> ret = new ArrayList<Project>();
//        for (UserProjectRel upr : uprs) {
//            ret.add(upr.getProject());
//        }
//        return ret;
        Query query = sess.createQuery("select upr.project from UserProjectRel as upr where upr.user=:user and upr.canManage=true order by upr.project.id");
        query.setParameter("user", user);
        return query.list();
    }

    @Override
    public int Insert(Project obj) {
        return super.Insert(obj);
    }

    @Override
    public int Update(Project obj) {
        return super.Update(obj);
    }

    @Override
    public List<Project> findProjects() {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Project where id!=1 and status=1 order by id");
        List<Project> ret = query.list();
        return ret;
    }

    @Override
    public List<Project> findAll(int maxNum, int startNum) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Project order by id");//where id!=1 
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }
        return query.list();
    }
}
