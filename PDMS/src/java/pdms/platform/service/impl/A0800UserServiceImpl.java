package pdms.platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.ReplyDao;
import pdms.components.dao.TopicDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Identity;
import pdms.components.pojo.Project;
import pdms.components.pojo.Reply;
import pdms.components.pojo.Topic;
import pdms.components.pojo.User;
import pdms.components.vo.A0201TopicVo;
import pdms.components.vo.A0800UserVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0800UserService;
import pdms.platform.util.RoleUtil;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0800UserServiceImpl implements A0800UserService {

    private static Log logger = LogFactory.getLog(A0800UserServiceImpl.class);
    private UserDao userDao;
    private ProjectDao projectDao;
    private TopicDao topicDao;
    private ReplyDao replyDao;

    @Override
    public A0800UserVo MakeVo(String loginId) throws PdmsException {

        A0800UserVo vo = new A0800UserVo();

        //用户信息
        User user = userDao.findByLoginId(loginId);
        vo.setUsrId(user.getLoginid());
        vo.setUsrName(user.getName());

        //Identity
        StringBuffer sb = new StringBuffer();
        Set<Identity> identities = user.getIdentities();
        int cnt = 0;
        for (Identity i : identities) {
            sb.append(i.getName());
            if (cnt++ != identities.size() - 1) {
                sb.append(",");
            }
        }
        vo.setUsrIdent(sb.toString());

        //fproject
        List<Project> projects = projectDao.findByUserLoginId(loginId);
        sb = new StringBuffer();
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getStatus() == -1) {
                continue;
            }
            sb.append(projects.get(i).getName());
            if (i != projects.size() - 1) {
                sb.append(",");
            }
        }
        vo.setFPrj(sb.toString());

        //最新主题
        List<A0201TopicVo> ltTopicVo = new ArrayList<A0201TopicVo>();
        List<Topic> ltTopic = topicDao.findLT(10, loginId);
        for (Topic t : ltTopic) {
            A0201TopicVo a0201 = new A0201TopicVo();
            a0201.setText(StringUtil.delFtmlTag(t.getName()));
            a0201.setTitle("主题: " + t.getName());
            a0201.setUrl("../pages/topic.jsp?id=" + t.getId());
            ltTopicVo.add(a0201);
        }
        vo.setLtTopicVo(ltTopicVo);

        //最新回复
        List<A0201TopicVo> lrTopicVo = new ArrayList<A0201TopicVo>();
        List<Reply> lrReply = replyDao.findLR(10, loginId);
        for (Reply r : lrReply) {
            A0201TopicVo a0201 = new A0201TopicVo();
            a0201.setText(StringUtil.delFtmlTag(r.getSummary()));
            a0201.setTitle("主题: " + r.getTopic().getName());
            a0201.setUrl("../pages/topic.jsp?mode=r&id=" + r.getTopic().getId());
            lrTopicVo.add(a0201);
        }
        vo.setLrTopicVo(lrTopicVo);

        return vo;
    }

    @Override
    public boolean changePsw(String loginId, String psw, String pswOld) throws PdmsException {

        User usr = userDao.findByLoginId(loginId);
        if (usr == null) {
            logger.error("无此用户");
            return false;
        }
        String rule = RoleUtil.getOperate("Personal", usr);
        if (!rule.contains("U")) {
            logger.error("Personal权限认证失败");
            return false;
        }

//        logger.info("pswOld " + pswOld);
//        logger.info("usr.getPassword() " + usr.getPassword());
        if (!StringUtil.getMD5Code(pswOld).equals(usr.getPassword())) {
            logger.error("原密码错误");
            return false;
        }

        usr.setPassword(StringUtil.getMD5Code(psw));
        int updateStatus = userDao.Update(usr);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新发生错误");
            return false;
        }
        return true;
    }

    @Override
    public boolean changeUsrInfo(String oldLoginId, String loginId, String name) throws PdmsException {

        User usr = userDao.findByLoginId(oldLoginId);
        if (usr == null) {
            logger.error("无此用户");
            return false;
        }
        String rule = RoleUtil.getOperate("Personal", usr);
        if (!rule.contains("U")) {
            logger.error("Personal权限认证失败");
            return false;
        }

        usr.setLoginid(loginId);
        usr.setName(name);
        int updateStatus = userDao.Update(usr);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新发生错误");
            return false;
        }
        return true;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public ReplyDao getReplyDao() {
        return replyDao;
    }

    public void setReplyDao(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    public TopicDao getTopicDao() {
        return topicDao;
    }

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
