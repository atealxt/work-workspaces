package pdms.components.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Project;
import pdms.components.pojo.User;
import pdms.components.pojo.UserProjectRel;
import pdms.platform.configeration.DIManager;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class UserDaoImpl extends EasyDao implements UserDao {

    @Override
    public User findByLoginIdAndPsw(String loginId, String psw) {
        StringBuffer sb = new StringBuffer();
        sb.append("from User where loginid='");
        sb.append(loginId);
        sb.append("' ");
        sb.append("and password='");
        sb.append(psw);
        sb.append("'");
        return (User) super.SelectUnique(sb.toString());
    }

    @Override
    public User findByLoginId(String loginId) {
        StringBuffer sb = new StringBuffer();
        sb.append("from User where loginid='");
        sb.append(loginId);
        sb.append("'");
        return (User) super.SelectUnique(sb.toString());
    }

    @Override
    public List<User> findByProjectId(int projectId) {
        ProjectDao projectDao = (ProjectDao) DIManager.getBean("ProjectDao");
        Project project = projectDao.findById(projectId);
        if (project == null) {
            return new ArrayList<User>(0);
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from UserProjectRel where project=:project order by id");
        query.setParameter("project", project);

        List<UserProjectRel> uprs = query.list();
        if (uprs == null || uprs.size() == 0) {
            return new ArrayList<User>(0);
        }
        List<User> ret = new ArrayList<User>();
        for (UserProjectRel upr : uprs) {
            ret.add(upr.getUser());
        }
        return ret;
    }

    @Override
    public List<User> findByProjectId(int projectId, boolean canManage) {
        ProjectDao projectDao = (ProjectDao) DIManager.getBean("ProjectDao");
        Project project = projectDao.findById(projectId);
        if (project == null) {
            return new ArrayList<User>(0);
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from UserProjectRel where project=:project and canManage=:canManage order by id");
        query.setParameter("project", project);
        query.setParameter("canManage", canManage);

        List<UserProjectRel> uprs = query.list();
        if (uprs == null || uprs.size() == 0) {
            return new ArrayList<User>(0);
        }
        List<User> ret = new ArrayList<User>();
        for (UserProjectRel upr : uprs) {
            ret.add(upr.getUser());
        }
        return ret;
    }

    @Override
    public int Insert(User obj) {
        return super.Insert(obj);
    }

    @Override
    public int Update(User obj) {
        return super.Update(obj);
    }

    @Override
    public List<User> findAll(int maxNum, int startNum) {

//        System.out.println(isUpdating(User.class));
//        while (isUpdating(User.class)) {
//            System.out.println("waiting...");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException ex) {
//            }
//        }
        return findAll(maxNum, startNum, "from User order by status desc,loginid asc");
    }

    @Override
    public int CountAll() {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        String sql = "select count(*) from User";
        Query query = sess.createQuery(sql);
        Long l = (Long) query.iterate().next();
        return l.intValue();
    }

    @Override
    public List<User> findAllManager(int maxNum, int startNum) {
        StringBuffer querySQL = new StringBuffer();
        //SELECT * FROM User u WHERE u.id in (
        //SELECT ui.user_id from User_Identity_Relation ui,Identity i
        //WHERE u.id = ui.user_id AND ui.identity_id = i.id AND i.name = '项目负责人')
        querySQL.append("select i.users from Identity as i where i.name = '项目负责人'");
        return findAll(maxNum, startNum, querySQL.toString());
    }

    @Override
    public List<User> findAllUsrs(int maxNum, int startNum) {
        StringBuffer querySQL = new StringBuffer();
        querySQL.append("select i.users from Identity as i where i.name = '职员' order by status desc,loginid asc");
        return findAll(maxNum, startNum, querySQL.toString());
    }

    @Override
    public User findById(int id) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from User where id=:id order by id");
        query.setParameter("id", id);

        return (User) query.uniqueResult();
    }

    @Override
    public int Delete(User obj) {
        return super.Delete(obj);
    }
}
