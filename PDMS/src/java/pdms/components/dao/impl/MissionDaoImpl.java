package pdms.components.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.MissionDao;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Mission;
import pdms.components.pojo.MissionSubmit;
import pdms.components.pojo.Project;
import pdms.components.pojo.User;
import pdms.platform.configeration.DIManager;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class MissionDaoImpl extends EasyDao implements MissionDao {

    private ProjectDao projectDao;
    private UserDao userDao;

    @Override
    public List<Mission> findByUserLoginId(String loginId) {
        return findByUserLoginId(loginId, -1, 0);
    }

    @Override
    public List<Mission> findByUserLoginId(String loginId, int maxNum, int startNum) {
        return findByUserLoginIdAndCond(loginId, maxNum, startNum, null);
    }

    @Override
    public List<Mission> findByUserLoginIdAndCond(String loginId, int maxNum, int startNum, String conditions) {
        if (userDao == null) {
            userDao = (UserDao) DIManager.getBean("UserDao");
        }
        User user = userDao.findByLoginId(loginId);
        if (user == null) {
            return new ArrayList<Mission>(0);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("from Mission where receiver=:receiver ");
        if (conditions != null) {
            sb.append("and content like '%");
            sb.append(conditions);
            sb.append("%' ");
        }
        sb.append("order by inspectStatus asc,");//审核状态
        sb.append("completeStatus asc,");//完成状态
        sb.append("distributionConfirm asc,");//受取状态
        sb.append("completetimeLimit asc,");//完成日限
        sb.append("confirmtimeLimit asc,");//确认截至时间
        sb.append("createtime desc,");//任务建立时间
        sb.append("id asc");//id

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery(sb.toString());
        query.setParameter("receiver", user);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }
        query.setFirstResult(startNum);

        List<Mission> missions = query.list();
        return missions;
    }

    @Override
    public List<Mission> findByProjectId(int prjId, int maxCnt) {
        return findByProjectId(prjId, maxCnt, 0);
    }

    @Override
    public List<Mission> findByProjectId(int prjId, int maxNum, int startNum) {
        return findByPIdAndCond(prjId, maxNum, startNum, null);
    }

    @Override
    public List<Mission> findByPIdAndCond(int prjId, int maxNum, int startNum, String conditions) {
        if (projectDao == null) {
            projectDao = (ProjectDao) DIManager.getBean("ProjectDao");
        }
        Project prj = projectDao.findById(prjId);

        StringBuffer sb = new StringBuffer();
        sb.append("from Mission where project=:project ");
        if (conditions != null) {
            sb.append("and content like '%");
            sb.append(conditions);
            sb.append("%' ");
        }
        sb.append("order by inspectStatus asc,");//审核状态
        sb.append("completeStatus asc,");//完成状态
        sb.append("distributionConfirm asc,");//受取状态
        sb.append("completetimeLimit asc,");//完成日限
        sb.append("confirmtimeLimit asc,");//确认截至时间
        sb.append("createtime desc,");//任务建立时间
        sb.append("id asc");//id

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery(sb.toString());
        query.setParameter("project", prj);
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }
        return query.list();
    }

    @Override
    public List<Mission> find4ChartByProjectId(int prjId, int maxNum, int startNum) {
        if (projectDao == null) {
            projectDao = (ProjectDao) DIManager.getBean("ProjectDao");
        }
        Project prj = projectDao.findById(prjId);

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Mission where project=:project and completeStatus=true order by id asc");
        query.setParameter("project", prj);
        query.setFirstResult(startNum);
        if (maxNum != -1) {
            query.setMaxResults(maxNum);
        }
        return query.list();
    }

    @Override
    public Mission findById(int id) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Mission where id=:id order by id");
        query.setParameter("id", id);
        return (Mission) query.uniqueResult();
    }

    @Override
    public Mission findBySubmitFileId(String fileId) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from Mission where submitInfo in (from MissionSubmit where uploadFile = (from UploadFile where id= :id))");
        query.setParameter("id", fileId);
        return (Mission) query.uniqueResult();
    }

    @Override
    public int Update(Mission obj) {
        return super.Update(obj);
    }

    @Override
    public int Insert(MissionSubmit obj) {
        return super.Insert(obj);
    }

    @Override
    public int Insert(Mission obj) {
        return super.Insert(obj);
    }

    @Override
    public int Delete(Mission obj) {
        return super.Delete(obj);
    }

    @Override
    public int Count4Chart(int projectId) {
        if (projectDao == null) {
            projectDao = (ProjectDao) DIManager.getBean("ProjectDao");
        }
        Project prj = projectDao.findById(projectId);

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("select count(*) from Mission where project=:project and completeStatus=true order by id ");
        query.setParameter("project", prj);
        Long l = (Long) query.iterate().next();
        return l.intValue();
    }

    @Override
    public int CountByTime4Chart(Date start, Date end) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        String sql = "select count(*) from Mission as m where m.completeStatus=true and m.submitInfo.submitDate>=:start and m.submitInfo.submitDate<:end order by id";
        Query query = sess.createQuery(sql);
        query.setParameter("start", start);
        query.setParameter("end", end);
        Long l = (Long) query.iterate().next();
        return l.intValue();
    }

    @Override
    public int CountAllByTime4Chart(Date start, Date end) {
        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        String sql = "select count(*) from Mission as m where (m.createtime>=:start or m.completeStatus=false) and m.createtime<:end order by id";
        Query query = sess.createQuery(sql);
        query.setParameter("start", start);
        query.setParameter("end", end);
        Long l = (Long) query.iterate().next();
        return l.intValue();
    }
}
