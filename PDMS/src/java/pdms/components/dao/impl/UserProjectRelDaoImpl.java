package pdms.components.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import pdms.components.dao.UserProjectRelDao;
import pdms.components.pojo.Project;
import pdms.components.pojo.User;
import pdms.components.pojo.UserProjectRel;
import pdms.platform.configeration.DaoManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class UserProjectRelDaoImpl extends EasyDao implements UserProjectRelDao {

    private static Log logger = LogFactory.getLog(UserProjectRelDaoImpl.class);

    @Override
    public int InsOrUpd(User usr, Project prj, boolean manage) {

        Session sess = DaoManager.getSession();
//        Session sess = DaoManager.getSessionFactory().openSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from UserProjectRel where user=:user and project=:project order by id");
        query.setParameter("user", usr);
        query.setParameter("project", prj);
        UserProjectRel upr = (UserProjectRel) query.uniqueResult();

        int iStatus = 0;
        if (upr == null) {
            upr = new UserProjectRel();
            upr.setProject(prj);
            upr.setUser(usr);
            upr.setCanManage(manage);
//            return super.Insert(upr);
            iStatus = super.Insert(upr);
        } else {
            upr.setProject(prj);
            upr.setUser(usr);
            upr.setCanManage(manage);
//            return super.Update(upr);
            iStatus = super.Update(upr);
        }
//        try {
//            DaoManager.getSessionFactory().evict(User.class);
//        } catch (Exception ex) {
//            logger.error(ex.getMessage());
//        }
        return iStatus;
    }

    @Override
    public int delAllRelation(Project prj) {

        Session sess = DaoManager.getSession();
        sess.beginTransaction();
        Query query = sess.createQuery("from UserProjectRel where project=:project order by id");
        query.setParameter("project", prj);
        List<UserProjectRel> uprs = query.list();
        int iRet = 0;
        for (UserProjectRel upr : uprs) {
//            if (upr.isCanManage()) {
//                iRet = super.Delete(upr);
//            }
            iRet = super.Delete(upr);
        }

        return iRet;
    }
}
