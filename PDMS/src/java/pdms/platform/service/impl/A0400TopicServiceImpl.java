package pdms.platform.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.ReplyDao;
import pdms.components.dao.TopicDao;
import pdms.components.dao.UserDao;
import pdms.components.dto.A0400TopicDto;
import pdms.components.pojo.Project;
import pdms.components.pojo.Reply;
import pdms.components.pojo.Topic;
import pdms.components.pojo.User;
import pdms.components.vo.A0400TopicVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0400TopicService;
import pdms.platform.util.RoleUtil;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;
import static pdms.platform.constant.TopicConstant.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0400TopicServiceImpl implements A0400TopicService {

    private static Log logger = LogFactory.getLog(A0400TopicServiceImpl.class);
    private ProjectDao projectDao;
    private UserDao userDao;
    private TopicDao topicDao;
    private ReplyDao replyDao;

    @Override
    public String createTopic(A0400TopicDto dto) throws PdmsException {

        User user = userDao.findByLoginId(dto.getLoginId());
        String rule = RoleUtil.getOperate("Topic", user);
        if (!rule.contains("C")) {
            logger.error("无发帖权限");
            return "{success:false,errors:'无发帖权限！'}";
        } //if (!limit(dto, "Topic")) {
        else if (!limit(dto, "Topic")) {
            return "{success:false,errors:'无此操作权限!'}";
        }

        Project p = projectDao.findById(Integer.parseInt(dto.getPid()));
        User u = userDao.findByLoginId(dto.getLoginId());

        Topic topic = new Topic();
        String name = "";
        String title = dto.getTTitle();
        if (title != null && !title.trim().equals("")) {
            name = "[" + title + "]" + dto.getTName();
        } else {
            name = dto.getTName();
        }
        topic.setName(name);
        if (dto.getTLevel().equals("Position 1")) {
            topic.setTopiclevel(LEVEL_POSITION_1);
        } else {
            topic.setTopiclevel(LEVEL_POSITION_2);
        }
        if (dto.getTRole().equals("Private")) {
            topic.setTopictype(TYPE_PRIVATE);
        } else {
            topic.setTopictype(TYPE_PUBLIC);
        }
        topic.setContent(Hibernate.createClob(dto.getTContent()));
        topic.setProject(p);
        topic.setCreatetime(new Date());
        topic.setStatus(STATUS_OPEN);
        topic.setCreateuser(u);

        int insertStatus = topicDao.Insert(topic);
        if (insertStatus == DB_STATUS_NG) {
            logger.error("发帖失败！");
            return "{success:false,errors:'发帖失败！'}";
        }
        return "{success:true}";
    }

    @Override
    public String editTopic(A0400TopicDto dto, String tId, String rId) throws PdmsException {

        if (rId != null && !rId.equals("")) {
            //是编辑回复内容,而不是主题内容
            Reply r = replyDao.findById(rId);
            if (r == null) {
                logger.error("编辑失败！回复帖ID错误！");
                return "{success:false,errors:'编辑失败！回复帖ID错误！'}";
            }
            Topic topic = r.getTopic();
            if (topic == null) {
                logger.error("编辑失败！主题ID错误！");
                return "{success:false,errors:'编辑失败！主题ID错误！'}";
            }
            if (topic.getStatus() != STATUS_OPEN) {
                logger.error("编辑失败！主题已关闭！");
                return "{success:false,errors:'编辑失败！主题已关闭！'}";
            }

            StringBuffer sb = new StringBuffer(dto.getTContent());
//            sb.append("<hr>此帖被");
//            sb.append(topic.getCreateuser().getName());
//            sb.append("于");
//            sb.append(StringUtil.getDateFormat(new Date(), "yyyy/MM/dd HH:mm:ss"));
//            sb.append("重新编辑");
            r.setContent(Hibernate.createClob(sb.toString()));

            int updateStatus = replyDao.Update(r);
            if (updateStatus == DB_STATUS_NG) {
                logger.error("更新失败！");
                return "{success:false,errors:'更新失败！'}";
            }
            return "{success:true}";
        }

        Topic topic = topicDao.findById(tId);
        if (topic == null) {
            logger.error("编辑失败！主题ID错误！");
            return "{success:false,errors:'编辑失败！主题ID错误！'}";
        }
        if (topic.getStatus() != STATUS_OPEN) {
            logger.error("编辑失败！主题已关闭！");
            return "{success:false,errors:'编辑失败！主题已关闭！'}";
        }

        if (!StringUtil.isEmpty(dto.getTLevel())) {
            if (dto.getTLevel().equals("Position 1")) {
                topic.setTopiclevel(LEVEL_POSITION_1);
            } else {
                topic.setTopiclevel(LEVEL_POSITION_2);
            }
        }
        if (!StringUtil.isEmpty(dto.getTRole())) {
            if (dto.getTRole().equals("Private")) {
                topic.setTopictype(TYPE_PRIVATE);
            } else {
                topic.setTopictype(TYPE_PUBLIC);
            }
        }

        if (!StringUtil.isEmpty(dto.getTContent())) {
            StringBuffer sb = new StringBuffer(dto.getTContent());
//            sb.append("<hr>此帖被");
//            sb.append(topic.getCreateuser().getName());
//            sb.append("于");
//            sb.append(StringUtil.getDateFormat(new Date(), "yyyy/MM/dd HH:mm:ss"));
//            sb.append("重新编辑");
            topic.setContent(Hibernate.createClob(sb.toString()));
        }
        if (!StringUtil.isEmpty(dto.getTName())) {
            String name = "";
            String title = dto.getTTitle();
            if (title != null && !title.trim().equals("")) {
                if (!dto.getTName().startsWith("[讨论]") &&
                    !dto.getTName().startsWith("[求助]") &&
                    !dto.getTName().startsWith("[资源]") &&
                    !dto.getTName().startsWith("[其他]")) {
                    name = "[" + title + "]" + dto.getTName();
                } else {
                    name = "[" + title + "]" + dto.getTName().substring(4);
                }
            } else {
                name = dto.getTName();
            }
            topic.setName(name);
        }

        int updateStatus = topicDao.Update(topic);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新失败！");
            return "{success:false,errors:'更新失败！'}";
        }
        return "{success:true}";
    }

    @Override
    public String createReply(String loginId, String tId, String tContent) throws PdmsException {

        Topic topic = topicDao.findById(tId);
        if (topic == null) {
            logger.error("回贴失败！主题ID错误！");
            return "{success:false,errors:'回贴失败！主题ID错误！'}";
        }
        if (topic.getStatus() != STATUS_OPEN) {
            logger.error("回贴失败！主题已关闭！");
            return "{success:false,errors:'回贴失败！主题已关闭！'}";
        }

        A0400TopicDto dto = new A0400TopicDto();
        dto.setLoginId(loginId);
        Project prj = topic.getProject();
        if (prj != null) {
            dto.setPid(Integer.toString(prj.getId()));
        }

        User user = userDao.findByLoginId(loginId);
        String rule = RoleUtil.getOperate("Reply", user);
        if (!rule.contains("C")) {
            logger.error("无回帖权限");
            return "{success:false,errors:'无回帖权限！'}";
        } //if (!limit(dto, "Reply")) {
        else if (!limit(dto, "Reply")) {
//            //判断是否是Public帖&Position2，不是则不能回复
//            if (topic.getTopictype() == TYPE_PRIVATE || topic.getTopiclevel() == LEVEL_POSITION_1) {
//                logger.error("权限验证失败！");
//                return "{success:false,errors:'无此帖回复权限！'}";
//            }
            logger.error("权限验证失败！");
            return "{success:false,errors:'无此帖回复权限！'}";
        }

        Reply r = new Reply();
        r.setContent(Hibernate.createClob(tContent));
        r.setCreatetime(new Date());
        r.setCreateuser(user);
        r.setTopic(topic);
        int insertStatus = replyDao.Insert(r);
        if (insertStatus == DB_STATUS_NG) {
            logger.error("回帖发生异常！");
            return "{success:false,errors:'回帖发生异常！'}";
        }

        return "{success:true}";
    }

    /** 权限验证 */
    private boolean limit(A0400TopicDto dto, String type) {

        String pId = dto.getPid();
        if (pId == null) {
            return true;
        }
        int i_pId = Integer.parseInt(pId);

        //如果是站务服务区，则放开权限(今后可能追加其他放开权限)
        if (i_pId == 1) {
            return true;
        }

        //所属专区判断
        List<Project> ps = projectDao.findByUserLoginId(dto.getLoginId());
        for (Project p : ps) {
            if (p.getId() == i_pId) {
                return true;
            }
        }

//        //可管理专区判断
//        ps = projectDao.findByManagerLoginId(dto.getLoginId());
//        for (Project p : ps) {
//            if (p.getId() == Integer.parseInt(dto.getPid())) {
//                return true;
//            }
//        }
//        //直接权限判断
//        User usr = userDao.findByLoginId(dto.getPid());
//        //identity
//        for (Identity i : usr.getIdentities()) {
//            if (i.getId() < 3) {
//                return true;
//            }
//        }
//        //role
//        for (Role r : usr.getRoles()) {
//            if (r.getFunction().getName().equals(type) && r.getOperate().getId() < 3) {
//                return true;
//            }
//        }

        return false;
    }

    @Override
    public List<A0400TopicVo> MakeVo(String tId, int maxNum, int startNum) throws PdmsException {

        List<A0400TopicVo> ret = new ArrayList<A0400TopicVo>();

        Topic t = topicDao.findById(tId);
        A0400TopicVo vo;
        if (startNum <= 0) {
            vo = new A0400TopicVo();
            vo.setIsTopic(true);
            try {
                vo.setRContent(t.getContent().getSubString(1, (int) t.getContent().length()));
            } catch (SQLException ex) {
                logger.error(ex);
            }
            vo.setRId(t.getId());
            vo.setRTitle(t.getName());
            vo.setRUsr(t.getCreateuser().getName());
            vo.setRTime(StringUtil.getDateFormat(t.getCreatetime(), "yyyy/MM/dd HH:mm:ss"));
            ret.add(vo);
        }

        List<Reply> replys = replyDao.findByTopicId(tId, maxNum, startNum);
        for (Reply r : replys) {
            vo = new A0400TopicVo();
            try {
                vo.setRContent(r.getContent().getSubString(1, (int) r.getContent().length()));
            } catch (SQLException ex) {
                logger.error(ex);
            }
            vo.setRId(r.getId());
            vo.setRTitle("Re: " + t.getName());
            vo.setRUsr(r.getCreateuser().getName());
            vo.setRTime(StringUtil.getDateFormat(r.getCreatetime(), "yyyy/MM/dd HH:mm:ss"));
            ret.add(vo);
        }

        return ret;
    }

    @Override
    public int getSumCount(String tId) throws PdmsException {
        return replyDao.findByTopicId(tId, -1).size() + 1;//要算上楼主
    }

    @Override
    public String closeTopic(List<String> list, String loginId) throws PdmsException {

        String ret = "{success:true}";

        User user = userDao.findByLoginId(loginId);
        if (user == null) {
            return "{success:false,errors:'无此用户'}";
        }
        String rule = RoleUtil.getOperate("Topic", user);
        boolean canDelAny = rule.contains("D");

        for (String i : list) {
            Topic t = topicDao.findById(i);
            if (t == null) {
                return "{success:false,errors:'主题帖ID错误'}";
            }
            if (t.getStatus() != STATUS_OPEN) {
                //帖子已关闭
                continue;
            }
            if (!canDelAny && !user.equals(t.getCreateuser())) {
                return "{success:false,errors:'无此权限'}";
            } else {
                t.setStatus(STATUS_CLOSE);
                int uStatus = topicDao.Update(t);
                if (uStatus == DB_STATUS_NG) {
                    return "{success:false,errors:'关闭主题帖中发生错误'}";
                }
            }
        }

        return ret;
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
