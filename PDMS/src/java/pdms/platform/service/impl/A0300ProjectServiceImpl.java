package pdms.platform.service.impl;

import java.util.ArrayList;
import java.util.List;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.ReplyDao;
import pdms.components.dao.TopicDao;
import pdms.components.pojo.Project;
import pdms.components.pojo.Topic;
import pdms.components.vo.A0300ProjectVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0300ProjectService;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.TopicConstant.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0300ProjectServiceImpl implements A0300ProjectService {

    private ProjectDao projectDao;
    private TopicDao topicDao;
    private ReplyDao replyDao;

    @Override
    public Project MakeVo(String projectId) throws PdmsException {
        return projectDao.findById(Integer.parseInt(projectId));
    }

    @Override
    public List<A0300ProjectVo> MakeTopicVo(String pId, String loginId, int maxNum, int startNum) throws PdmsException {

        List<A0300ProjectVo> ret = new ArrayList<A0300ProjectVo>();
        List<Topic> topics = topicDao.findByLoginIdAndPid(loginId, Integer.parseInt(pId), maxNum, startNum);
        for (Topic t : topics) {
            A0300ProjectVo vo = new A0300ProjectVo();
            vo.setTId(t.getId());
            vo.setTLevel(t.getTopiclevel().toString());

            StringBuffer name = new StringBuffer();
            //name.append("<a onclick=\"parent.addTab('../topic/A0400TopicAction.action?id=");
            name.append("<a onclick=\"parent.addTab('../pages/topic.jsp?id=");
            name.append(t.getId());
            name.append("','主题: ");
            name.append(t.getName());
            name.append("')\">");
            name.append(t.getName());
            name.append("</a>");
            //vo.setTName(t.getName());
            vo.setTName(name.toString());

            vo.setTUser(t.getCreateuser().getName());
            vo.setTDate(StringUtil.getDateFormat(t.getCreatetime(), "yyyy/MM/dd HH:mm:ss"));
            vo.setTRCnt(Integer.toString(replyDao.findByTopicId(t.getId(), -1).size()));

            int status = t.getStatus();
            if (status == STATUS_OPEN) {
                vo.setTStatus("开放");
            } else if (status == STATUS_CLOSE) {
                vo.setTStatus("关闭");
            } else {
                vo.setTStatus("其他");
            }

            ret.add(vo);
        }

        return ret;
    }

    @Override
    public int getSumCount(String pId, String loginId) throws PdmsException {
        return topicDao.findByLoginIdAndPid(loginId, Integer.parseInt(pId), -1, 0).size();
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
}

