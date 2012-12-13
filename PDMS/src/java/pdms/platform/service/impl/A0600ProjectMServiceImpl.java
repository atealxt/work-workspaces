package pdms.platform.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.IdentityDao;
import pdms.components.dao.MissionDao;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.UserDao;
import pdms.components.dao.UserProjectRelDao;
import pdms.components.dto.A0600ProjectMDto;
import pdms.components.pojo.Mission;
import pdms.components.pojo.Project;
import pdms.components.pojo.User;
import pdms.components.vo.A0600ProjectMVo;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0600ProjectMService;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0600ProjectMServiceImpl implements A0600ProjectMService {

    private static Log logger = LogFactory.getLog(A0600ProjectMServiceImpl.class);
    private ProjectDao projectDao;
    private UserDao userDao;
    private IdentityDao identityDao;
    private UserProjectRelDao uprDao;
    private MissionDao missionDao;

    @Override
    public List<A0600ProjectMVo> MakeVo(int maxNum, int startNum) throws PdmsException {

        List<A0600ProjectMVo> ret = new ArrayList<A0600ProjectMVo>();
        List<Project> projs = projectDao.findAll(maxNum, startNum);
        for (Project p : projs) {
            A0600ProjectMVo vo = new A0600ProjectMVo();
            vo.setPId(Integer.toString(p.getId()));
            vo.setPName(p.getName());

            if (p.getStarttime() != null) {
                vo.setPSd(StringUtil.getDateFormat(p.getStarttime(), "yyyy/MM/dd"));
            }
            if (p.getEndtime() != null) {
                vo.setPEd(StringUtil.getDateFormat(p.getEndtime(), "yyyy/MM/dd"));
            }

            int status = p.getStatus();
            if (status == 1) {
                vo.setPStatus("开放");
            } else if (status == -1) {
                vo.setPStatus("关闭");
            } else {
                vo.setPStatus("其他");
            }

            StringBuffer sb = new StringBuffer();
            List<User> managers = userDao.findByProjectId(p.getId(), true);
            for (int i = 0; i < managers.size(); i++) {
                sb.append(managers.get(i).getLoginid());
                if (i != managers.size() - 1) {
                    sb.append(",");
                }
            }
            vo.setPMan(sb.toString());

            sb = new StringBuffer();
            List<User> users = userDao.findByProjectId(p.getId(), false);
            for (int i = 0; i < users.size(); i++) {
                sb.append(users.get(i).getLoginid());
                if (i != users.size() - 1) {
                    sb.append(",");
                }
            }
            vo.setPUsrs(sb.toString());

            ret.add(vo);
        }
        return ret;
    }

    @Override
    public int getSumCount() throws PdmsException {
        return projectDao.findAll(-1, 0).size();
    }

    @Override
    public String getManagers() {
        StringBuffer ret = new StringBuffer("[");

        Set<User> managers = identityDao.findByName("项目负责人").getUsers();
        int cnt = 0;
        for (User u : managers) {

            ret.append("['");
            ret.append(u.getLoginid());

            if (cnt++ != managers.size() - 1) {
                ret.append("'],");
            } else {
                ret.append("']");
            }
        }

        ret.append("]");
        return ret.toString();
    }

    @Override
    public String update(A0600ProjectMDto dto) {

        String ret = "{success:true}";

        String id = dto.getId();
        String pName = dto.getPName();
        String pMan = dto.getPMan();
        String pUsrs = dto.getPUsrs();
        String pSd = dto.getPSd();
        String pEd = dto.getPEd();
        String status = dto.getStatus();

        Project project = projectDao.findById(Integer.parseInt(id));
        if (project == null) {
            return "{success:false,errors:'项目id错误'}";
        }

        project.setName(pName);
        try {
            project.setStarttime(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(pSd).getTime()));
            project.setEndtime(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(pEd).getTime()));
        } catch (ParseException ex) {
            logger.error(ex);
        }


        if ("开放".equals(status)) {
            project.setStatus(1);
        } /*else if ("关闭".equals(status)) {
        project.setStatus(-1);
        } else if ("其他".equals(status)) {
        project.setStatus(0);
        }*/

        int delUprStatus = uprDao.delAllRelation(project);
        if (delUprStatus == DB_STATUS_NG) {
            return "{success:false,errors:'更新管理权限错误'}";
        }

        String[] managers = pMan.split(",");
        for (String s : managers) {
            if (!s.equals("")) {
                User usr = userDao.findByLoginId(s);
                if (usr == null) {
                    return "{success:false,errors:'用户id错误'}";
                }
                int iouStatus = uprDao.InsOrUpd(usr, project, true);
                if (iouStatus == 1) {
                    return "{success:false,errors:'更新管理权限错误'}";
                }
            }
        }
        String[] users = pUsrs.split(",");
        for (String s : users) {
            if (!s.equals("")) {
                User usr = userDao.findByLoginId(s);
                if (usr == null) {
                    return "{success:false,errors:'用户id错误'}";
                }
                int iouStatus = uprDao.InsOrUpd(usr, project, false);
                if (iouStatus == 1) {
                    return "{success:false,errors:'更新管理权限错误'}";
                }
            }
        }
        int uStatus = projectDao.Update(project);
        if (uStatus == DB_STATUS_NG) {
            return "{success:false,errors:'更新中发生错误'}";
        }

        return ret;
    }

    @Override
    public String createNew(A0600ProjectMDto dto) {

        String ret = "{success:true}";

        String pName = dto.getPName();
        String pMan = dto.getPMan();
        String pSd = dto.getPSd();
        String pEd = dto.getPEd();
        String status = dto.getStatus();

        Project project = new Project();

        project.setName(pName);
        try {
            project.setStarttime(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(pSd).getTime()));
            project.setEndtime(new java.sql.Date(new SimpleDateFormat("yyyy年MM月dd日").parse(pEd).getTime()));
        } catch (ParseException ex) {
            logger.error(ex);
        }

        if ("开放".equals(status)) {
            project.setStatus(1);
        } else if ("关闭".equals(status)) {
            project.setStatus(-1);
        //} else if ("其他".equals(status)) {
        } else {
            project.setStatus(0);
        }
        int insertStatus = projectDao.Insert(project);
        if (insertStatus == DB_STATUS_NG) {
            return "{success:false,errors:'新建中发生错误'}";
        }

        if (StringUtil.isEmpty(pMan)) {
            return ret;
        }
        //update manager
        String[] managers = pMan.split(",");
        for (String s : managers) {
            if (!s.equals("")) {
                User usr = userDao.findByLoginId(s);
                if (usr == null) {
                    return "{success:false,errors:'用户id错误'}";
                }
                int iouStatus = uprDao.InsOrUpd(usr, project, true);
                if (iouStatus == 1) {
                    return "{success:false,errors:'更新管理权限错误'}";
                }
            }
        }
        return ret;
    }

    @Override
    public String delPrj(List<String> list) throws PdmsException {

        String ret = "{success:true}";

        for (String i : list) {
            Project project = projectDao.findById(Integer.parseInt(i));
            if (project == null) {
                return "{success:false,errors:'项目id错误'}";
            }

            List<Mission> ms = missionDao.findByProjectId(project.getId(), 1);
            if (ms.size() > 0 && !ms.get(0).isCompleteStatus()) {
                //如果有残留任务，不能关闭
                String sTemp = "{success:false,errors:'项目 " + project.getName() + " 存在未完成任务，请完成或取消任务后再关闭项目!'}";
                return sTemp;
            }

            //修改结束日为当前日期
            project.setEndtime(new Date(new java.util.Date().getTime()));
            //修改状态(因可能选择多条，所以当状态已经是-1的时候也不能直接return)
            project.setStatus(-1);

            //不要取消现有项目和人员的关系(暂定)

            //更新
            int uStatus = projectDao.Update(project);
            if (uStatus == DB_STATUS_NG) {
                return "{success:false,errors:'关闭项目中发生错误'}";
            }
        }

        return ret;
    }

    public IdentityDao getIdentityDao() {
        return identityDao;
    }

    public void setIdentityDao(IdentityDao identityDao) {
        this.identityDao = identityDao;
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

    public UserProjectRelDao getUprDao() {
        return uprDao;
    }

    public void setUprDao(UserProjectRelDao uprDao) {
        this.uprDao = uprDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
