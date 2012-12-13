package pdms.components.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pdms.components.dao.ReplyDao;
import pdms.components.dao.TopicDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Reply;
import pdms.components.pojo.Topic;
import pdms.components.pojo.User;
import pdms.platform.configeration.DIManager;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class ReplyDaoImpl extends EasyDao implements ReplyDao {

    private UserDao userDao;

    @Override
    public List<Reply> findByUserLoginId(String loginId, int maxNum) {
        if (maxNum <= 0) {
            return new ArrayList<Reply>(0);
        }
        if (userDao == null) {
            userDao = (UserDao) DIManager.getBean("UserDao");
        }
        User user = userDao.findByLoginId(loginId);
        if (user == null) {
            return new ArrayList<Reply>(0);
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Criteria criteria = sess.createCriteria(Reply.class);
        criteria.add(Restrictions.eq("createuser", user));
        criteria.setMaxResults(maxNum);
        List<Reply> ret = criteria.list();
        return ret;
    }

    @Override
    public List<Reply> findByTopicId(String topicId, int maxNum) {
        return findByTopicId(topicId, maxNum, 0);
    }

    @Override
    public List<Reply> findByTopicId(String topicId, int maxNum, int startNum) {
        TopicDao topicDao = (TopicDao) DIManager.getBean("TopicDao");
        Topic topic = topicDao.findById(topicId);
        if (topic == null) {
            return new ArrayList<Reply>(0);
        }

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Reply where topic=:topic order by createtime asc,id asc");
        query.setParameter("topic", topic);

        if (startNum != -1) {
            query.setFirstResult(startNum);
        } else {
            //取最后10条
            Query qTemp = sess.createQuery("select count(*) from Reply where topic=:topic");
            qTemp.setParameter("topic", topic);
            Long l = (Long) qTemp.iterate().next();
            int cnt = l.intValue();
            query.setFirstResult(cnt - 10 < 0 ? 0 : cnt - 10);
        }

        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }
        List<Reply> ret = query.list();
        return ret;
    }

    @Override
    public List<Reply> findLR(int maxNum) {
        return findLR(maxNum, null);
    }

    @Override
    public List<Reply> findLR(int maxNum, String loginId) {
        if (maxNum <= 0) {
            return new ArrayList<Reply>(0);
        }
        if (loginId != null) {
            if (userDao == null) {
                userDao = (UserDao) DIManager.getBean("UserDao");
            }
            User user = userDao.findByLoginId(loginId);
            Session sess = DaoManager.getSession();
            sess.beginTransaction();
            Query query = sess.createQuery("from Reply where createuser=:createuser order by createtime desc,id asc");
            query.setParameter("createuser", user);
            query.setMaxResults(maxNum);
            //List<Reply> ret = query.list();
            List<Reply> ret = query.setCacheable(true).list();
            return ret;
        } else {
            Session sess = DaoManager.getSession();
            sess.beginTransaction();
            Query query = sess.createQuery("from Reply order by createtime desc,id asc");
            query.setMaxResults(maxNum);
            List<Reply> ret = query.setCacheable(true).list();
            return ret;
        }
    }

    @Override
    public int Insert(Reply obj) {
        return super.Insert(obj);
    }

    @Override
    public Reply findById(String id) {
        StringBuffer sb = new StringBuffer();
        sb.append("from Reply where id='");
        sb.append(id);
        sb.append("'");
        return (Reply) super.SelectUnique(sb.toString());
    }

    @Override
    public int Update(Reply obj) {
        return super.Update(obj);
    }
}
