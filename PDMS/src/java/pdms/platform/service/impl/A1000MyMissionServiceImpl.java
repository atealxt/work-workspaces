package pdms.platform.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import pdms.components.dao.FileDao;
import pdms.components.dao.MissionDao;
import pdms.components.pojo.Mission;
import pdms.components.pojo.MissionSubmit;
import pdms.components.pojo.Project;
import pdms.components.pojo.UploadFile;
import pdms.components.vo.A1000MyMissionVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1000MyMissionService;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;
import static pdms.platform.constant.MissionConstant.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1000MyMissionServiceImpl implements A1000MyMissionService {

    private MissionDao missionDao;
    private FileDao fileDao;

    @Override
    public List<A1000MyMissionVo> MakeVo(String loginId, int maxNum, int startNum, String conditions) throws
        PdmsException {

        List<A1000MyMissionVo> ret = new ArrayList<A1000MyMissionVo>();
        List<Mission> missions = null;
        if (!StringUtil.isEmpty(conditions)) {
            missions = missionDao.findByUserLoginIdAndCond(loginId, maxNum, startNum, conditions);
        } else {
            missions = missionDao.findByUserLoginId(loginId, maxNum, startNum);
        }
        for (Mission m : missions) {
            A1000MyMissionVo vo = new A1000MyMissionVo();
            vo.setMId(Integer.toString(m.getId()));
            vo.setMName(m.getContent());
            vo.setMDist(m.getDistributor().getName());

            Project p = m.getProject();
            if (p != null) {
                vo.setMPrj(p.getName());
            } else {
                vo.setMPrj("-");
            }
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
                    break;
                case INSPECT_STATUS_OK:
                    vo.setMIs("是");
                    break;
                case INSPECT_STATUS_ERROR:
                    vo.setMIs("未通过");
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

                long leftTime = 0;
                if (confirmtimeLimit != null) {
                    leftTime = confirmtimeLimit.getTime() - new java.util.Date().getTime();
                }
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
    public int getSumCount(String loginId, String conditions) throws PdmsException {

        if (!StringUtil.isEmpty(conditions)) {
            return missionDao.findByUserLoginIdAndCond(loginId, -1, 0, conditions).size();
        } else {
            return missionDao.findByUserLoginId(loginId, -1, 0).size();
        }
    }

    @Override
    public String receiveMission(List<String> list) throws PdmsException {

        String ret = "{success:true}";

        for (String i : list) {
            Mission mission = missionDao.findById(Integer.parseInt(i));
            if (mission == null) {
                return "{success:false,errors:'任务id错误'}";
            }

            mission.setDistributionConfirm(DISTRIBUTION_CONFIRM_OK);
            int updateStatus = missionDao.Update(mission);
            if (updateStatus == DB_STATUS_NG) {
                StringBuffer sTemp = new StringBuffer("{success:false,errors:'接收任务发生错误. id:");
                sTemp.append(mission.getId());
                sTemp.append("'}");
                return sTemp.toString();
            }
        }

        return ret;
    }

    @Override
    public String submitMission(String id, String content, String fileId) throws PdmsException {

        String ret = "{success:true}";

        Mission mission = missionDao.findById(Integer.parseInt(id));
        if (mission == null) {
            return "{success:false,errors:'任务id错误'}";
        }

        //判断是否超过完成时限
        if (mission.getCompletetimeLimit().getTime() - new java.util.Date().getTime() <= 0) {
            return "{success:false,errors:'已超过任务完成日限，不能提交'}";
        }

        MissionSubmit ms = new MissionSubmit();
        ms.setSubmitInfo(content);
        ms.setMission(mission);
        ms.setSubmitDate(new java.util.Date());

        //附件
        if (!StringUtil.isEmpty(fileId)) {
            UploadFile uf = fileDao.findById(fileId);
            if (uf == null) {
                return "{success:false,errors:'文件(附件)ID错误'}";
            }
            ms.setUploadFile(uf);
        }

        int insertStatus = missionDao.Insert(ms);
        if (insertStatus == DB_STATUS_NG) {
            StringBuffer sTemp = new StringBuffer("{success:false,errors:'提交任务发生错误. id:");
            sTemp.append(mission.getId());
            sTemp.append("'}");
            return sTemp.toString();
        }

        mission.setSubmitInfo(ms);
        mission.setCompleteStatus(COMPLETE_STATUS_YES);//已完成

        //再次提交请求验收
        if (mission.getInspectStatus() == INSPECT_STATUS_ERROR) {
            mission.setInspectStatus(INSPECT_STATUS_AGAIN);
        }

        int updateStatus = missionDao.Update(mission);
        if (updateStatus == DB_STATUS_NG) {
            StringBuffer sTemp = new StringBuffer("{success:false,errors:'提交任务发生错误. id:");
            sTemp.append(mission.getId());
            sTemp.append("'}");
            return sTemp.toString();
        }
        return ret;
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
}
