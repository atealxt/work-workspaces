package pdms.platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.ReplyDao;
import pdms.components.dao.TopicDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Project;
import pdms.components.pojo.Reply;
import pdms.components.pojo.Topic;
import pdms.components.pojo.User;
import pdms.components.vo.A0200MainVo;
import pdms.components.vo.A0201TopicVo;
import pdms.components.vo.A0202ProjectVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0200MainService;
import pdms.platform.util.RoleUtil;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.ProjectConstant.STATUS_CLOSE;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0200MainServiceImpl implements A0200MainService {

    private UserDao userDao;
    private ProjectDao projectDao;
    private TopicDao topicDao;
    private ReplyDao replyDao;
    /** 用户所属项目专区 */
    private List<Project> projectsFollowed;
    private List<Project> projectsManaged;

    @Override
    public String MakeTreeInfo(String loginId, String nodeId) {
        StringBuffer tree = new StringBuffer();
        tree.append("[");

        if ("root".equals(nodeId)) {
            //共有链接
            tree.append("{id:'main',iconCls: 'icon_tree_main',text:'主页',leaf:true},");
            tree.append("{id:'search',iconCls: 'icon_tree_search',text:'搜索',leaf:true,myurl:'../pages/search.jsp'},");
            tree.append("{id:'p_serv',iconCls: 'icon_tree_ser',text:'站务服务区',leaf:true,myurl:'../project/A0300ProjectAction.action?id=1'},");

            //特定用户专用链接
            tree.append(getManagerLink(loginId));
        } else if ("project".equals(nodeId)) {
            //取得所属项目Link
            tree.append(getProjectLink(loginId));
        } else if ("fprojectm".equals(nodeId)) {
            //取得所属项目管理Link
            tree.append(getFProjectMLink(loginId));
        }

        tree.append("]");
        return tree.toString();
    }

    /**
     * 取得特殊权限
     */
    private String getManagerLink(String loginId) {
        StringBuffer sb = new StringBuffer();

        if (projectsFollowed == null) {
            initProjectsFollowed(loginId);
        }
        if (projectsManaged == null) {
            initProjectsManaged(loginId);
        }
        User user = userDao.findByLoginId(loginId);

        //我的任务
        String rule = RoleUtil.getOperate("Mission", user);
        if (rule.contains("R")) {
            sb.append("{id:'mymisson',iconCls: 'icon_tree_mis',text:'我的任务',leaf:true,myurl:'../pages/mymission.jsp'},");
        }

        //我的文件
        rule = RoleUtil.getOperate("File", user);
        if (rule.contains("R") && rule.contains("U")) {
            sb.append("{id:'file',iconCls: 'icon_tree_file',text:'我的文件',leaf:true,myurl:'../pages/myfile.jsp'},");
        }

        //报表统计
        rule = RoleUtil.getOperate("Report", user);
        if (rule.contains("R") && rule.contains("U")) {
            sb.append("{id:'report',iconCls: 'icon_tree_rep',text:'报表统计',leaf:true,myurl:'../chart/A1300ChartAction.action'},");
        }

        //会员管理
        rule = RoleUtil.getOperate("User", user);
        if (rule.contains("C") && rule.contains("D")) {
            sb.append("{id:'userm',iconCls: 'icon_tree_userm',text:'会员管理',leaf:true,myurl:'../pages/userm.jsp'},");
        }

        //用户组管理
        rule = RoleUtil.getOperate("Group", user);
        if (rule.contains("C") && rule.contains("D")) {
            sb.append("{id:'grpm',iconCls: 'icon_tree_grpm',text:'用户组管理',leaf:true,myurl:'../pages/groupm.jsp'},");
        }

        //文件管理
        rule = RoleUtil.getOperate("File", user);
        if (rule.contains("C") && rule.contains("D")) {
            sb.append("{id:'filem',iconCls: 'icon_tree_filem',text:'文件管理',leaf:true,myurl:'../pages/myfile.jsp?mode=m'},");
        }

        //项目管理/所属项目管理
        rule = RoleUtil.getOperate("Project", user);
        if (rule.contains("C") && rule.contains("D")) {
            sb.append("{id:'projectm',iconCls: 'icon_tree_prjm',text:'项目管理',leaf:true,myurl:'../pages/projectm.jsp'},");
        }
        if (rule.contains("U") && projectsManaged.size() != 0) {
            sb.append("{id:'fprojectm',text:'所属项目管理',leaf:false},");
        }

        //所属项目专区
        if (projectsFollowed.size() != 0) {
            sb.append("{id:'project',text:'进入项目专区',leaf:false},");
        }

        return sb.toString();
    }

    /**
     * 取得所属项目Link
     */
    private String getProjectLink(String loginId) {
        StringBuffer sb = new StringBuffer();

        if (projectsFollowed == null) {
            initProjectsFollowed(loginId);
        }
        for (int i = 0; i < projectsFollowed.size(); i++) {

            sb.append("{id:'project_");
            sb.append(i);
            sb.append("',text:'");
            //sb.append("项目: ");
            sb.append(projectsFollowed.get(i).getName());
            sb.append("',qtip:'");
            sb.append(projectsFollowed.get(i).getName());
            sb.append("',leaf:true,myurl:'../project/A0300ProjectAction.action?id=");
            sb.append(projectsFollowed.get(i).getId());
            sb.append("'},");
        }

        return sb.toString();
    }

    /**
     * 取得可管理项目Link
     */
    private String getFProjectMLink(String loginId) {
        StringBuffer sb = new StringBuffer();

        if (projectsManaged == null) {
            initProjectsManaged(loginId);
        }
        for (int i = 0; i < projectsManaged.size(); i++) {

            sb.append("{id:'fprojectm_");
            sb.append(i);
            sb.append("',text:'");
            sb.append("管理:");
            sb.append(projectsManaged.get(i).getName());
            sb.append("',qtip:'");
            sb.append("管理:");
            sb.append(projectsManaged.get(i).getName());
            sb.append("',leaf:true,myurl:'../fprojectm/A0700FProjectMAction.action?id=");
            sb.append(projectsManaged.get(i).getId());
            sb.append("'},");
        }

        return sb.toString();
    }

    /** 取得用户所属项目 */
    private void initProjectsFollowed(String loginId) {
        projectsFollowed = new ArrayList<Project>();
        List<Project> ps = projectDao.findByUserLoginId(loginId);
        for (int i = 0; i < ps.size(); i++) {
            if (ps.get(i).getStatus() == STATUS_CLOSE) {
                //排除已关闭项目
                continue;
            }
            projectsFollowed.add(ps.get(i));
        }
    }

    /** 取得用户可管理项目 */
    private void initProjectsManaged(String loginId) {
        projectsManaged = new ArrayList<Project>();
        List<Project> ps = projectDao.findByManagerLoginId(loginId);
        for (int i = 0; i < ps.size(); i++) {
            if (ps.get(i).getStatus() == STATUS_CLOSE) {
                //排除已关闭项目
                continue;
            }
            projectsManaged.add(ps.get(i));
        }
    }

    @Override
    public A0200MainVo MakeVo(String loginId) throws PdmsException {

        A0200MainVo vo = new A0200MainVo();

        //用户名
        User user = userDao.findByLoginId(loginId);
        String userName = user.getName();
        vo.setUserName(userName);

        //最新主题
        List<A0201TopicVo> ltTopicVo = new ArrayList<A0201TopicVo>();
        List<Topic> ltTopic = topicDao.findLT(10);
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
        List<Reply> lrReply = replyDao.findLR(10);
        for (Reply r : lrReply) {
            A0201TopicVo a0201 = new A0201TopicVo();
            //a0201.setText(StringUtil.delFtmlTag(r.getSummary()));
            a0201.setText(r.getSummary());//has already filter html tag
            a0201.setTitle("主题: " + r.getTopic().getName());
            a0201.setUrl("../pages/topic.jsp?mode=r&id=" + r.getTopic().getId());
            lrTopicVo.add(a0201);
        }
        vo.setLrTopicVo(lrTopicVo);

        //未完结主题
        List<A0201TopicVo> luTopicVo = new ArrayList<A0201TopicVo>();
        List<Topic> luTopic = topicDao.findLU(10);
        for (Topic t : luTopic) {
            A0201TopicVo a0201 = new A0201TopicVo();
            a0201.setText(StringUtil.delFtmlTag(t.getName()));
            a0201.setTitle("主题: " + t.getName());
            a0201.setUrl("../pages/topic.jsp?id=" + t.getId());
            luTopicVo.add(a0201);
        }
        vo.setLuTopicVo(luTopicVo);

        //各种专区(项目)
        List<A0202ProjectVo> projectVo = new ArrayList<A0202ProjectVo>();
        List<Project> projects = projectDao.findProjects();
        for (Project p : projects) {
            A0202ProjectVo a0202 = new A0202ProjectVo();
            a0202.setLogo(p.getLogo());
            a0202.setName(p.getName());
            a0202.setSummary(p.getSummary());
            a0202.setTitle("项目: " + p.getName());
            a0202.setUrl("../project/A0300ProjectAction.action?id=" + p.getId());
            a0202.setDevelopDate(StringUtil.getDateFormat(p.getStarttime()) + " - " + StringUtil.getDateFormat(p.getEndtime()));
            a0202.setMenberCnt(userDao.findByProjectId(p.getId()).size());
            projectVo.add(a0202);
        }
        vo.setProjectVo(projectVo);

        return vo;
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
