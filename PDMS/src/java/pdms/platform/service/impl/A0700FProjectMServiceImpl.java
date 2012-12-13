package pdms.platform.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.FileDao;
import pdms.components.dao.MissionDao;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.UserDao;
import pdms.components.dto.A1000MyMissionDto;
import pdms.components.pojo.Mission;
import pdms.components.pojo.MissionSubmit;
import pdms.components.pojo.Project;
import pdms.components.pojo.UploadFile;
import pdms.components.pojo.User;
import pdms.components.vo.A1000MyMissionVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0700FProjectMService;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;
import static pdms.platform.constant.MissionConstant.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0700FProjectMServiceImpl implements A0700FProjectMService {

    private static Log logger = LogFactory.getLog(A0700FProjectMServiceImpl.class);
    private ProjectDao projectDao;
    private FileDao fileDao;
    private MissionDao missionDao;
    private UserDao userDao;

    @Override
    public Project getProject(String id) {
        Project p = projectDao.findById(Integer.parseInt(id));
        if (p == null) {
            logger.error("项目ID错误");
        }
        return p;
    }

    @Override
    public String getPrjUsrs(String pId) {
        StringBuffer sb = new StringBuffer();
        List<User> usrs = userDao.findByProjectId(Integer.parseInt(pId));
        for (int i = 0; i < usrs.size(); i++) {
            sb.append(usrs.get(i).getName());
            sb.append("(");
            sb.append(usrs.get(i).getLoginid());
            sb.append(")");
            if (i != usrs.size() - 1) {
                sb.append(" ,");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean chgPrjAn(String id, String announcement) {
        Project p = projectDao.findById(Integer.parseInt(id));
        if (p == null) {
            logger.error("项目ID错误");
            return false;
        }
        p.setAnnouncement(announcement);

        int updateStatus = projectDao.Update(p);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新出错");
            return false;
        }
        return true;
    }

    @Override
    public boolean chgPrjLOGO(String id, String logo, String fileId) {

        Project p = projectDao.findById(Integer.parseInt(id));
        if (p == null) {
            logger.error("项目ID错误");
            return false;
        }

        if (!StringUtil.isEmpty(fileId)) {
            UploadFile uf = fileDao.findById(fileId);
            if (uf == null) {
                logger.error("文件ID错误");
                return false;
            }
            p.setLogo(uf.getAddress());
        } else {
            p.setLogo(logo);
        }

        int updateStatus = projectDao.Update(p);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新出错");
            return false;
        }

        return true;
    }

    @Override
    public List<A1000MyMissionVo> MakeVo(String pId, int maxNum, int startNum, String conditions) throws PdmsException {
        List<A1000MyMissionVo> ret = new ArrayList<A1000MyMissionVo>();
        List<Mission> missions = null;
        if (!StringUtil.isEmpty(conditions)) {
            missions = missionDao.findByPIdAndCond(Integer.parseInt(pId), maxNum, startNum, conditions);
        } else {
            missions = missionDao.findByProjectId(Integer.parseInt(pId), maxNum, startNum);
        }
        for (Mission m : missions) {
            A1000MyMissionVo vo = new A1000MyMissionVo();
            vo.setMId(Integer.toString(m.getId()));
            vo.setMName(m.getContent());
            vo.setMDist(m.getDistributor().getName());

            if (m.isDistribution()) {
                vo.setMD("是");
                vo.setMRece(m.getReceiver().getName());
            } else {
                vo.setMD("否");
                vo.setMRece("-");
            }

//            Project p = m.getProject();
//            if (p != null) {
//                vo.setMPrj(p.getName());
//            } else {
//                vo.setMPrj("-");
//            }
            vo.setMComtime(StringUtil.getDateFormat(m.getCompletetimeLimit(), "yyyy/MM/dd"));

            //受取状态
            int distributionConfirm = m.getDistributionConfirm();
            switch (distributionConfirm) {
                case DISTRIBUTION_CONFIRM_NO:
                    vo.setMDc("否");
                    break;
                case DISTRIBUTION_CONFIRM_OK:
                    vo.setMDc("是");
                    break;
                case DISTRIBUTION_CONFIRM_ERROR:
                    vo.setMDc("超时");//未按时受取
                    break;
                default:
                    break;
            }

            //完成状态
            if (m.isCompleteStatus()) {
                vo.setMCs("是");

                MissionSubmit ms = m.getSubmitInfo();
                if (ms != null) {
                    vo.setMSubmit(ms.getSubmitInfo());
                }
            } else if (!m.getCompletetimeLimit().after(new java.util.Date())) {
                vo.setMCs("超时");//超过规定完成日限
            } else {
                vo.setMCs("否");
            }

            //审核状态
            int inspectStatus = m.getInspectStatus();
            switch (inspectStatus) {
                case INSPECT_STATUS_NO:
                    vo.setMIs("否");
                    vo.setMInspe("-");
                    break;
                case INSPECT_STATUS_OK:
                    vo.setMIs("是");
                    vo.setMInspe(m.getInspector().getName());
                    break;
                case INSPECT_STATUS_ERROR:
                    vo.setMIs("未通过");
                    vo.setMInspe(m.getInspector().getName());
                    break;
                case INSPECT_STATUS_AGAIN:
                    vo.setMIs("再次请求审核");
                    break;
                default:
                    break;
            }

            //所剩确认时间
            if (distributionConfirm == DISTRIBUTION_CONFIRM_OK || m.isCompleteStatus()) {
                vo.setMConfirm("-");
            } else {
                Date confirmtimeLimit = m.getConfirmtimeLimit();
                long daytime = 24 * 3600 * 1000;
                long leftTime = confirmtimeLimit.getTime() - new java.util.Date().getTime();
                if (leftTime <= 0) {
                    vo.setMConfirm("超时未接收");
                } else {
                    long days = leftTime / daytime;
                    if (days == 0) {
                        vo.setMConfirm("一天以内");
                    } else {
                        vo.setMConfirm(days + "天");
                    }
                }
            }

            ret.add(vo);
        }

        return ret;
    }

    @Override
    public int getSumCount(String pId, String conditions) throws PdmsException {
        if (!StringUtil.isEmpty(conditions)) {
            return missionDao.findByPIdAndCond(Integer.parseInt(pId), -1, 0, conditions).size();
        } else {
            return missionDao.findByProjectId(Integer.parseInt(pId), -1, 0).size();
        }
    }

    @Override
    public String addMission(String loginId, A1000MyMissionDto dto) {

        Project p = projectDao.findById(Integer.parseInt(dto.getPId()));
        if (p == null) {
            logger.error("项目ID错误");
            return "{success:false,errors:'项目ID错误!'}";
        }

        User usr = userDao.findByLoginId(loginId);
        if (usr == null) {
            logger.error("用户LoginId错误");
            return "{success:false,errors:'用户LoginId错误!'}";
        }

        Mission mission = new Mission();
        mission.setProject(p);
        mission.setContent(dto.getT_content());
        mission.setDistributor(usr);
        mission.setCreatetime(new java.sql.Date(new Date().getTime()));
        mission.setDistributionConfirm(DISTRIBUTION_CONFIRM_NO);
        mission.setCompleteStatus(COMPLETE_STATUS_NO);
        mission.setInspectStatus(INSPECT_STATUS_NO);
        try {
            mission.setCompletetimeLimit(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(dto.getCompletlimit()).getTime()));
            mission.setConfirmtimeLimit(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(dto.getConfirmlimit()).getTime()));
        } catch (ParseException ex) {
            logger.error(ex);
        }

        int insertStatus = missionDao.Insert(mission);
        if (insertStatus == DB_STATUS_NG) {
            logger.error("新建任务发生错误");
            return "{success:false,errors:'新建任务发生错误!'}";
        }

        return "{success:true}";
    }

    @Override
    public String editMission(String loginId, A1000MyMissionDto dto) {

        Mission mission = missionDao.findById(Integer.parseInt(dto.getMId()));
        if (mission == null) {
            logger.error("Mission Id错误");
            return "{success:false,errors:'Mission Id错误!'}";
        }
        if (!mission.getDistributor().getLoginid().equals(loginId)) {
            logger.error("您不能修改别人创建的任务!");
            return "{success:false,errors:'您不能修改别人创建的任务!'}";
        }

        mission.setContent(dto.getT_content());
        try {
            mission.setCompletetimeLimit(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(dto.getCompletlimit()).getTime()));
            mission.setConfirmtimeLimit(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(dto.getConfirmlimit()).getTime()));
        } catch (ParseException ex) {
            logger.error(ex);
        }
        int updateStatus = missionDao.Update(mission);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新任务发生错误");
            return "{success:false,errors:'更新任务发生错误!'}";
        }

        return "{success:true}";
    }

    @Override
    public String distMission(String mId, String uId) {

        Mission mission = missionDao.findById(Integer.parseInt(mId));
        if (mission == null) {
            logger.error("Mission Id错误");
            return "{success:false,errors:'Mission Id错误!'}";
        }

        User usr = userDao.findById(Integer.parseInt(uId));
        if (usr == null) {
            logger.error("用户Id错误");
            return "{success:false,errors:'用户Id错误!'}";
        }

        if (!mission.getCompletetimeLimit().after(new java.util.Date())) {
            logger.error("已超过规定完成日限！不能分配！");//超过规定完成日限
            return "{success:false,errors:'已超过规定完成日限！不能分配！'}";
        }

        //notice that clean other properties
        mission.setReceiver(usr);
        mission.setDistributionConfirm(DISTRIBUTION_CONFIRM_NO);
        mission.setCompleteStatus(COMPLETE_STATUS_NO);
        mission.setInspectStatus(INSPECT_STATUS_NO);

        Date cnfirmtimeLimit = mission.getConfirmtimeLimit();
        if (cnfirmtimeLimit != null && cnfirmtimeLimit.getTime() - new Date().getTime() < 0) {
            //未得到有效确认
            //延时一天确认
            logger.info("延时一天确认");
            mission.setConfirmtimeLimit(new java.sql.Date(new Date().getTime() + 3600 * 1000 * 24 * 1));
        }

        int updateStatus = missionDao.Update(mission);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新任务发生错误");
            return "{success:false,errors:'更新任务发生错误!'}";
        }
        return "{success:true}";
    }

    @Override
    public boolean delMission(List<String> list) throws PdmsException {

        for (String i : list) {
            Mission mission = missionDao.findById(Integer.parseInt(i));
            int delStatus = missionDao.Delete(mission);
            if (delStatus == DB_STATUS_NG) {
                logger.error("删除过程中发生错误,任务ID:" + mission.getId());
                return false;
            }
        }

        return true;
    }

    @Override
    public String inspectMission(String uId, String mId, String passFlg) {

        Mission mission = missionDao.findById(Integer.parseInt(mId));
        if (mission == null) {
            logger.error("Mission Id错误");
            return "{success:false,errors:'Mission Id错误!'}";
        }

        User usr = userDao.findByLoginId(uId);
        if (usr == null) {
            logger.error("用户Id错误");
            return "{success:false,errors:'用户Id错误!'}";
        }

        mission.setInspector(usr);
        if ("true".equals(passFlg)) {
            mission.setInspectStatus(INSPECT_STATUS_OK);
        } else {
            mission.setInspectStatus(INSPECT_STATUS_ERROR);
        }

        int updateStatus = missionDao.Update(mission);
        if (updateStatus == DB_STATUS_NG) {
            logger.error("更新任务发生错误");
            return "{success:false,errors:'更新任务发生错误!'}";
        }
        return "{success:true}";
    }

    public FileDao getFileDao() {
        return fileDao;
    }

    public void setFileDao(FileDao fileDao) {
        this.fileDao = fileDao;
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
