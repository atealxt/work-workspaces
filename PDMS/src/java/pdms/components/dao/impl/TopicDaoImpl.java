package pdms.components.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.TopicDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Project;
import pdms.components.pojo.Topic;
import pdms.components.pojo.User;
import pdms.platform.configeration.DIManager;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class TopicDaoImpl extends EasyDao implements TopicDao {

    private UserDao userDao;

    @Override
    public Topic findById(String id) {
        StringBuffer sb = new StringBuffer();
        sb.append("from Topic where id='");
        sb.append(id);
        sb.append("'");
        return (Topic) super.SelectUnique(sb.toString());
    }

    @Override
    public List<Topic> findByUserLoginId(String loginId, int maxNum) {
        if (maxNum <= 0) {
            return new ArrayList<Topic>(0);
        }
        if (userDao == null) {
            userDao = (UserDao) DIManager.getBean("UserDao");
        }
        User user = userDao.findByLoginId(loginId);
        if (user == null) {
            return new ArrayList<Topic>(0);
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Criteria criteria = sess.createCriteria(Topic.class);
        criteria.add(Restrictions.eq("createuser", user));
        criteria.setMaxResults(maxNum);
        List<Topic> ret = criteria.list();
        return ret;
    }

    @Override
    public List<Topic> findByTopicLevel(int topiclevel, int maxNum) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Criteria criteria = sess.createCriteria(Topic.class);
        criteria.add(Restrictions.eq("topiclevel", topiclevel));
        criteria.setMaxResults(maxNum);
        List<Topic> ret = criteria.list();
        return ret;
    }

    @Override
    public List<Topic> findLT(int maxNum) {
        return findLT(maxNum, null);
    }

    @Override
    public List<Topic> findLT(int maxNum, String loginId) {
        if (maxNum <= 0) {
            return new ArrayList<Topic>(0);
        }

        if (loginId != null) {
            if (userDao == null) {
                userDao = (UserDao) DIManager.getBean("UserDao");
            }
            User user = userDao.findByLoginId(loginId);
            String queryStr = "from Topic where createuser=:createuser order by createtime desc,id asc";
            Session sess = DaoManager.getSession();
            sess.beginTransaction();
            Query query = sess.createQuery(queryStr);
            query.setParameter("createuser", user);
            query.setMaxResults(maxNum);
            //List<Topic> ret = query.list();
            List<Topic> ret = query.setCacheable(true).list();//use query cache
            return ret;
        } else {
            String queryStr = "from Topic where status!=-1 order by createtime desc,id asc";
            Session sess = DaoManager.getSession();
            sess.beginTransaction();
            Query query = sess.createQuery(queryStr);
            query.setMaxResults(maxNum);
            //List<Topic> ret = query.list();
            List<Topic> ret = query.setCacheable(true).list();//use query cache
            return ret;
        }

    }

    @Override
    public List<Topic> findLU(int maxNum) {
        if (maxNum <= 0) {
            return new ArrayList<Topic>(0);
        }
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Topic where status=1 order by createtime desc,id asc");
        query.setMaxResults(maxNum);
        List<Topic> ret = query.setCacheable(true).list();
        return ret;
    }

    @Override
    public List<Topic> findByLoginIdAndPid(String loginId, int pId, int maxNum, int startNum) {

        ProjectDao projectDao = (ProjectDao) DIManager.getBean("ProjectDao");
        Project project = projectDao.findById(pId);
        if (project == null) {
            return new ArrayList<Topic>(0);
        }

        //权限判断
        String strQuery = null;
        if (pId == 1) {
            //放开站务服务区(今后可追加新的取消过滤区)
            strQuery = "from Topic where project=:project and status!=-1 order by createtime desc,topiclevel asc,id asc";
        } else {
            strQuery = "from Topic where project=:project and status!=-1 and topictype=0 order by createtime desc,topiclevel asc,id asc";

            //List<Project> pManage = projectDao.findByManagerLoginId(loginId);
            List<Project> pFollowed = projectDao.findByUserLoginId(loginId);
            for (Project p : pFollowed) {
                if (p.getId() == project.getId()) {
                    strQuery = "from Topic where project=:project and status!=-1 order by createtime desc,topiclevel asc,id asc";
                    break;
                }
            }
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery(strQuery);
        query.setParameter("project", project);
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }

        return query.list();
    }

    @Override
    public int Insert(Topic obj) {
        return super.Insert(obj);
    }

    @Override
    public int Update(Topic obj) {
        return super.Update(obj);
    }
}
