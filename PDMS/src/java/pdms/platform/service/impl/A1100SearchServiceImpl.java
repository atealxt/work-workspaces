package pdms.platform.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.MissionDao;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.ReplyDao;
import pdms.components.dao.TopicDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Identity;
import pdms.components.pojo.Mission;
import pdms.components.pojo.Project;
import pdms.components.pojo.Reply;
import pdms.components.pojo.Topic;
import pdms.components.pojo.User;
import pdms.components.vo.A1100SearchVo;
import pdms.components.vo.A1101SearchDataVo;
import pdms.platform.constant.UserConstant;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1100SearchService;
import pdms.platform.util.RoleUtil;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1100SearchServiceImpl implements A1100SearchService {

    private static Log logger = LogFactory.getLog(A1100SearchServiceImpl.class);
    private TopicDao topicDao;
    private ReplyDao replyDao;
    private UserDao userDao;
    private ProjectDao projectDao;
    private MissionDao missionDao;
    private int maxNum;
    private int startNum;
    private String loginId;
    private User user;

    @Override
    public A1100SearchVo MakeVo(int maxNum, int startNum, String loginId, String condition) throws PdmsException {

        User u = userDao.findByLoginId(loginId);
        if (u == null) {
            logger.warn("用户ID错误：" + loginId);
            return getEmptyRet();
        }
        if (StringUtil.isEmpty(condition)) {
            logger.warn("条件为空！");
            return getEmptyRet();
        }

        this.maxNum = maxNum;
        this.startNum = startNum;
        this.loginId = loginId;
        this.user = u;

        //s12s21s31c<br>s12s22s31caaa<br>
        condition = condition.replace("<BR>", "<br>");//for ie
        String[] params = condition.split("<br>");

        //一级分类条件只可能有一个
        int s1 = Integer.parseInt(params[0].substring(params[0].indexOf("s1") + 2, params[0].indexOf("s1") + 3));
        //logger.info(s1);

        //添加header
        A1100SearchVo vo = getEmptyRet();

        //生成条件
        StringBuffer SQL = new StringBuffer();
        for (int i = 0; i < params.length; i++) {
            int s2 = Integer.parseInt(params[i].substring(params[i].indexOf("s2") + 2, params[i].indexOf("s2") + 3));
            int s3 = Integer.parseInt(params[i].substring(params[i].indexOf("s3") + 2, params[i].indexOf("s3") + 3));
            String searchChar = params[i].substring(params[i].indexOf("c") + 1);

//            logger.info(s2);
//            logger.info(s3);
//            logger.info(searchChar);

            switch (s1) {
                case 1:

                    if (i == 0 && s2 == 1) {
                        SQL.append("select t from Topic as t,User as u where t.createuser=u and ");
                    } else if (i == 0 && s2 == 2) {
                        SQL.append("select r from Reply as r,User as u where r.createuser=u and ");
                    }

                    if (s2 == 1 && s3 == 1) {
                        //帖子．主题帖．发表人用户ID = searchChar
                        SQL.append("u.loginid like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 1 && s3 == 2) {
                        //帖子．主题帖．发表人姓名 = searchChar
                        SQL.append("u.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 1 && s3 == 3) {
                        //帖子．主题帖．主题名 = searchChar
                        SQL.append("t.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 1 && s3 == 4) {
                        //帖子．主题帖．主题内容 = searchChar
                        SQL.append("t.content like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 2 && s3 == 1) {
                        //帖子．回帖．发表人用户ID = searchChar
                        SQL.append("u.loginid like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 2 && s3 == 2) {
                        //帖子．回帖．发表人姓名 = searchChar
                        SQL.append("u.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 2 && s3 == 3) {
                        //帖子．回帖．回复内容 = searchChar
                        SQL.append("r.content like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    }

                    if (i != params.length - 1) {
                        SQL.append("and ");
                    } else {
                        SQL.append("order by createtime desc ");
                    }
                    break;
                case 2:

                    if (s2 == 1 && s3 == 1) {
                        //用户．角色．系统管理员
                        SQL.append("select i.users from Identity as i where i.name = '系统管理员' order by status desc,loginid asc");
                    } else if (s2 == 1 && s3 == 2) {
                        //用户．角色．职员
                        SQL.append("select i.users from Identity as i where i.name = '职员' order by status desc,loginid asc");
                    } else if (s2 == 1 && s3 == 3) {
                        //用户．角色．项目负责人
                        SQL.append("select i.users from Identity as i where i.name = '项目负责人' order by status desc,loginid asc");
                    } else if (s2 == 2 && s3 == 1) {
                        //用户．其他．用户ID = searchChar
                        if (i == 0) {
                            SQL.append("from User where ");
                        }
                        SQL.append("loginid like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                        if (i != params.length - 1) {
                            SQL.append("and ");
                        }
                    } else if (s2 == 2 && s3 == 2) {
                        //用户．其他．用户名 = searchChar
                        if (i == 0) {
                            SQL.append("from User where ");
                        }
                        SQL.append("name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                        if (i != params.length - 1) {
                            SQL.append("and ");
                        }
                    } else if (s2 == 2 && s3 == 3) {
                        //用户．其他．公司IP = searchChar
                        if (i == 0) {
                            SQL.append("from User where ");
                        }
                        SQL.append("ip like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                        if (i != params.length - 1) {
                            SQL.append("and ");
                        }
                    }

                    break;
                case 3:

                    if (i == 0) {
                        SQL.append("select m from Mission as m,User as distributor,User as receiver,User as inspector where ");
                    }

                    if (s2 == 1 && s3 == 1) {
                        //任务．完成状态．已完成
                        SQL.append("m.completeStatus = true ");
                    } else if (s2 == 1 && s3 == 2) {
                        //任务．完成状态．未完成
                        SQL.append("m.completeStatus = false ");
                    } else if (s2 == 2 && s3 == 1) {
                        //任务．验收确认状态．已验收
                        SQL.append("m.inspectStatus != 1 ");
                    } else if (s2 == 2 && s3 == 2) {
                        //任务．验收确认状态．未验收
                        SQL.append("m.inspectStatus = 1 ");
                    } else if (s2 == 3 && s3 == 1) {
                        //任务．分配人．用户ID = searchChar
                        //where t.createuser=u
                        SQL.append("m.distributor = distributor and distributor.loginid like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 3 && s3 == 2) {
                        //任务．分配人．用户名 = searchChar
                        SQL.append("m.distributor = distributor and distributor.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 4 && s3 == 1) {
                        //任务．接收人．用户ID = searchChar
                        SQL.append("m.receiver = receiver and receiver.loginid like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 4 && s3 == 2) {
                        //任务．接收人．用户名 = searchChar
                        SQL.append("m.receiver = receiver and receiver.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 5 && s3 == 1) {
                        //任务．验收人．用户ID = searchChar
                        SQL.append("m.inspector = inspector and inspector.loginid like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 5 && s3 == 2) {
                        //任务．验收人．用户名 = searchChar
                        SQL.append("m.inspector = inspector and inspector.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 6 && s3 == 1) {
                        //任务．其他．任务ID = searchChar
                        SQL.append("m.id = '");
                        SQL.append(searchChar);
                        SQL.append("' ");
                    } else if (s2 == 6 && s3 == 2) {
                        //任务．其他．任务内容 = searchChar
                        SQL.append("m.content like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    }

                    if (i != params.length - 1) {
                        SQL.append("and ");
                    }
                    break;
                case 4:

                    if (i == 0 && s2 == 1) {
                        SQL.append("select DISTINCT upr.project from UserProjectRel as upr where ");
                    } else if (i == 0 && s2 == 2) {
                        SQL.append("from Project as p where ");
                    }

                    if (s2 == 1 && s3 == 1) {
                        //项目．项目成员．用户ID = searchChar
                        SQL.append("upr.user.loginid like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 1 && s3 == 2) {
                        //项目．项目成员．用户名 = searchChar
                        SQL.append("upr.user.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 2 && s3 == 1) {
                        //项目．其他．项目ID = searchChar
                        SQL.append("p.id like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 2 && s3 == 2) {
                        //项目．其他．项目名 = searchChar
                        SQL.append("p.name like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    } else if (s2 == 2 && s3 == 3) {
                        //项目．其他．项目简要 = searchChar
                        SQL.append("p.summary like '%");
                        SQL.append(searchChar);
                        SQL.append("%' ");
                    }

                    if (i != params.length - 1) {
                        SQL.append("and ");
                    }
                    break;
            }

        }

        //filter sql
        String SQLFilter;
        switch (s1) {
            case 1:
                SQLFilter = SQL.toString();
                if (SQLFilter.indexOf("u.") == -1) {
                    SQLFilter = SQLFilter.replace(",User as u where t.createuser=u and", "");
                }
                searchArticle(vo, SQLFilter);
                break;
            case 2:
                searchUser(vo, SQL.toString());
                break;
            case 3:
                SQLFilter = SQL.toString();
                if (SQLFilter.indexOf("m.distributor") == -1) {
                    SQLFilter = SQLFilter.replace("User as distributor,", "");
                }
                if (SQLFilter.indexOf("m.receiver") == -1) {
                    SQLFilter = SQLFilter.replace("User as receiver,", "");
                }
                if (SQLFilter.indexOf("m.inspector") == -1) {
                    SQLFilter = SQLFilter.replace(",User as inspector", "");
                }
                searchMission(vo, SQLFilter);
                break;
            case 4:
                searchProject(vo, SQL.toString());
                break;
        }

        logger.info(SQL);
        return vo;
    }

    /** 搜索帖子 */
    private void searchArticle(A1100SearchVo vo, String SQL) {

        if (SQL.startsWith("select t")) {
            addHeader(vo.getHeaders(), new String[]{"主题名", "主题内容", "发表时间", "发表人", "所在专区"});
            List<Topic> topics = topicDao.findAll(maxNum, startNum, SQL);
            String rule = RoleUtil.getOperate("Topic", user);
            for (Topic t : topics) {
                A1101SearchDataVo dataVo = new A1101SearchDataVo();
                dataVo.setCOl1(t.getName());
                try {
                    dataVo.setCOl2(t.getContent().getSubString(1, (int) t.getContent().length()));
                } catch (SQLException ex) {
                    logger.error(ex);
                }
                try {
                    dataVo.setCOl3(StringUtil.getDateFormat(t.getCreatetime(), "yyyy/MM/dd HH:mm:ss"));
                } catch (PdmsException ex) {
                    logger.error(ex);
                }
                dataVo.setCOl4(t.getCreateuser().getName());

                Project p = t.getProject();
                if (p != null) {
                    dataVo.setCOl5(p.getName());
                } else {
                    dataVo.setCOl5("");
                }

                boolean canView = t.getTopictype() == 0;
                if (p != null && !canView) {
                    //private topic and followed project topic filter
                    List<Project> pManage = projectDao.findByUserLoginId(loginId);
                    for (Project pTemp : pManage) {
                        if (pTemp.getId() == p.getId()) {
                            canView = true;
                            break;
                        }
                    }
                }

                if (rule.contains("R") && canView) {
                    StringBuffer link = new StringBuffer();
                    link.append("<a onclick=\"parent.addTab('../pages/topic.jsp?id=");
                    link.append(t.getId());
                    link.append("','主题: ");
                    link.append(t.getName());
                    link.append("')\">");
                    link.append("详细");
                    link.append("</a>");
                    dataVo.setLInk(link.toString());
                } else {
                    dataVo.setLInk("");
                }

                vo.getData().add(dataVo);
            }
            vo.setResults(topicDao.findAll(-1, 0, SQL).size());
        } else if (SQL.startsWith("select r")) {
            addHeader(vo.getHeaders(), new String[]{"回复内容", "发表时间", "发表人", "所属主题", "所属专区(项目)"});
            List<Reply> replys = replyDao.findAll(maxNum, startNum, SQL);
            String rule = RoleUtil.getOperate("Reply", user);

            for (Reply r : replys) {
                A1101SearchDataVo dataVo = new A1101SearchDataVo();
                try {
                    dataVo.setCOl1(r.getContent().getSubString(1, (int) r.getContent().length()));
                } catch (SQLException ex) {
                    logger.error(ex);
                }
                try {
                    dataVo.setCOl2(StringUtil.getDateFormat(r.getCreatetime(), "yyyy/MM/dd HH:mm:ss"));
                } catch (PdmsException ex) {
                    logger.error(ex);
                }
                dataVo.setCOl3(r.getCreateuser().getName());
                dataVo.setCOl4(r.getTopic().getName());

                Project p = r.getTopic().getProject();
                if (p != null) {
                    dataVo.setCOl5(p.getName());
                } else {
                    dataVo.setCOl5("");
                }

                boolean canView = r.getTopic().getTopictype() == 0;
                if (p != null && !canView) {
                    //private topic and followed project topic filter
                    List<Project> pManage = projectDao.findByUserLoginId(loginId);
                    for (Project pTemp : pManage) {
                        if (pTemp.getId() == p.getId()) {
                            canView = true;
                            break;
                        }
                    }
                }

                if (rule.contains("R") && canView) {
                    StringBuffer link = new StringBuffer();
                    link.append("<a onclick=\"parent.addTab('../pages/topic.jsp?id=");
                    link.append(r.getTopic().getId());
                    link.append("','主题: ");
                    link.append(r.getTopic().getName());
                    link.append("')\">");
                    link.append("详细");
                    link.append("</a>");
                    dataVo.setLInk(link.toString());
                } else {
                    dataVo.setLInk("");
                }

                vo.getData().add(dataVo);
            }
            vo.setResults(replyDao.findAll(-1, 0, SQL).size());
        }
    }

    /** 搜索用户 */
    private void searchUser(A1100SearchVo vo, String SQL) {

        addHeader(vo.getHeaders(), new String[]{"用户ID", "用户名", "公司IP", "角色", "状态"});

        String rule = RoleUtil.getOperate("User", user);
        List<User> users = userDao.findAll(maxNum, startNum, SQL);
        for (User u : users) {
            A1101SearchDataVo dataVo = new A1101SearchDataVo();
            dataVo.setCOl1(u.getLoginid());
            dataVo.setCOl2(u.getName());
            dataVo.setCOl3(u.getIp());
            Set<Identity> identities = u.getIdentities();
            StringBuffer sb = new StringBuffer();
            for (Identity i : identities) {
                sb.append(i.getName());
                sb.append(" ");
            }
            dataVo.setCOl4(sb.toString());

            int status = u.getStatus();
            if (status == UserConstant.STATUS_IN) {
                dataVo.setCOl5(UserConstant.STATUS_IN_SHOW);
            } else if (status == UserConstant.STATUS_OUT) {
                dataVo.setCOl5(UserConstant.STATUS_OUT_SHOW);
            } else {
                dataVo.setCOl5(UserConstant.STATUS_OTHER_SHOW);
            }

            if (rule.contains("C")) {
                StringBuffer link = new StringBuffer();
                link.append("<a onclick=\"parent.addTab('../pages/userm.jsp");
                link.append("','会员管理");
                link.append("')\">");
                link.append("详细");
                link.append("</a>");
                dataVo.setLInk(link.toString());
            } else {
                dataVo.setLInk("");
            }

            vo.getData().add(dataVo);
        }

        vo.setResults(userDao.findAll(-1, 0, SQL).size());
    }

    /** 搜索任务 */
    private void searchMission(A1100SearchVo vo, String SQL) {

        addHeader(vo.getHeaders(), new String[]{"任务内容摘要", "完成状态", "接收人", "分配人", "验收人"});

        String rule = RoleUtil.getOperate("Mission", user);
        List<Project> projects_MANAGE = projectDao.findByManagerLoginId(loginId);
        List<Project> projects_FOLLOW = projectDao.findByUserLoginId(loginId);
        List<Mission> missions = missionDao.findAll(maxNum, startNum, SQL);
        for (Mission m : missions) {
            A1101SearchDataVo dataVo = new A1101SearchDataVo();

            dataVo.setCOl1(m.getSummary());

            if (m.isCompleteStatus()) {
                dataVo.setCOl2("是");
            } else {
                dataVo.setCOl2("否");
            }

            User u = m.getReceiver();
            if (u != null) {
                dataVo.setCOl3(u.getName());
            } else {
                dataVo.setCOl3("");
            }
            u = m.getDistributor();
            if (u != null) {
                dataVo.setCOl4(u.getName());
            } else {
                dataVo.setCOl4("");
            }
            u = m.getInspector();
            if (u != null) {
                dataVo.setCOl5(u.getName());
            } else {
                dataVo.setCOl5("");
            }

            User receiver = m.getReceiver();
            boolean bTemp1 = false;
            boolean bTemp2 = false;
            Project prj = m.getProject();
            if (prj != null) {
                for (Project p : projects_MANAGE) {
                    if (p.getId() == prj.getId()) {
                        bTemp1 = true;
                        break;
                    }
                }
                for (Project p : projects_FOLLOW) {
                    if (p.getId() == prj.getId()) {
                        bTemp2 = true;
                        break;
                    }
                }
            }
            if (rule.contains("C") && bTemp1) {
                StringBuffer link = new StringBuffer();
                link.append("<a onclick=\"parent.addTab('../fprojectm/A0700FProjectMAction.action?id=");
                link.append(prj.getId());
                link.append("','管理:");
                link.append(prj.getName());
                link.append("')\">");
                link.append("详细");
                link.append("</a>");
                dataVo.setLInk(link.toString());
            } /*else if (rule.contains("R") && bTemp2) {
            StringBuffer link = new StringBuffer();
            link.append("<a onclick=\"parent.addTab('../project/A0300ProjectAction.action?id=");
            link.append(prj.getId());
            link.append("','");
            link.append(prj.getName());
            link.append("')\">");
            link.append("详细");
            link.append("</a>");
            dataVo.setLInk(link.toString());
            } */ else if (receiver != null && receiver.getLoginid().equals(loginId)) {
                StringBuffer link = new StringBuffer();
                link.append("<a onclick=\"parent.addTab('../pages/mymission.jsp");
                link.append("','我的任务");
                link.append("')\">");
                link.append("详细");
                link.append("</a>");
                dataVo.setLInk(link.toString());
            } else {
                dataVo.setLInk("");
            }

            vo.getData().add(dataVo);
        }

        vo.setResults(missionDao.findAll(-1, 0, SQL).size());
    }

    /** 搜索项目 */
    private void searchProject(A1100SearchVo vo, String SQL) {

        addHeader(vo.getHeaders(), new String[]{"项目名", "项目简要", "项目成员", "开发开始日", "开发结束日"});

        String rule = RoleUtil.getOperate("Project", user);
        List<Project> projects = projectDao.findAll(maxNum, startNum, SQL);
        for (Project p : projects) {
            A1101SearchDataVo dataVo = new A1101SearchDataVo();
            dataVo.setCOl1(p.getName());
            dataVo.setCOl2(p.getSummary());

            List<User> users = userDao.findByProjectId(p.getId());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < users.size(); i++) {
                sb.append(users.get(i).getName());
                sb.append("(");
                sb.append(users.get(i).getLoginid());
                sb.append(")");
                if (i != users.size() - 1) {
                    sb.append(",");
                }
            }
            dataVo.setCOl3(sb.toString());

            try {
                dataVo.setCOl4(StringUtil.getDateFormat(p.getStarttime()));
                dataVo.setCOl5(StringUtil.getDateFormat(p.getEndtime()));
            } catch (PdmsException ex) {
                logger.error(ex);
            }

            if (rule.contains("C")) {
                StringBuffer link = new StringBuffer();
                link.append("<a onclick=\"parent.addTab('../pages/projectm.jsp");
                link.append("','项目管理");
                link.append("')\">");
                link.append("详细");
                link.append("</a>");
                dataVo.setLInk(link.toString());
            } else if (rule.contains("R")) {
                StringBuffer link = new StringBuffer();
                link.append("<a onclick=\"parent.addTab('../project/A0300ProjectAction.action?id=");
                link.append(p.getId());
                link.append("','");
                link.append(p.getName());
                link.append("')\">");
                link.append("详细");
                link.append("</a>");
                dataVo.setLInk(link.toString());
            } else {
                dataVo.setLInk("");
            }

            vo.getData().add(dataVo);
        }

        vo.setResults(projectDao.findAll(-1, 0, SQL).size());
    }

    /** 添加header */
    private void addHeader(List<String> headers, String[] adds) {
        for (String s : adds) {
            headers.add(s);
        }
    }

    /** 取得空集 */
    private A1100SearchVo getEmptyRet() {
        A1100SearchVo vo = new A1100SearchVo();
        vo.setData(new ArrayList<A1101SearchDataVo>());
        vo.setHeaders(new ArrayList<String>());
        vo.setResults(0);
        return vo;
    }

    public MissionDao getMissionDao() {
        return missionDao;
    }

    public void setMissionDao(MissionDao missionDao) {
        this.missionDao = missionDao;
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
