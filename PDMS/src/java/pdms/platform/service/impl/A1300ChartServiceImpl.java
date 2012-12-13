package pdms.platform.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.MissionDao;
import pdms.components.dao.ProjectDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Mission;
import pdms.components.pojo.Project;
import pdms.components.vo.A1300PieVo;
import pdms.components.vo.A1301ColumnVo;
import pdms.components.vo.A1302GAndSVo;
import pdms.platform.configeration.StartUpConfig;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A1300ChartService;
import pdms.platform.util.ChartUtil;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1300ChartServiceImpl implements A1300ChartService {

    private MissionDao missionDao;
    private ProjectDao projectDao;
    private UserDao userDao;
    private static Log logger = LogFactory.getLog(A1300ChartServiceImpl.class);

    @Override
    public String getPieDataFilePath() throws PdmsException {

        //判断是否已有
        StringBuffer dir = null;
        if (StartUpConfig.REALPATH != null) {
            dir = new StringBuffer(StartUpConfig.REALPATH.replace("\\", "/")).append("amcharts/ampie/data/");
        } else {
            dir = new StringBuffer("C:/pdms/amcharts/ampie/data/");
        }

        Date date = new Date();
        dir.append(StringUtil.getDateFormat(date, "yyyyMM"));

        File fDir = new File(dir.toString());
        if (!fDir.exists()) {
            logger.info("新建目录" + fDir.getPath());
            boolean b = fDir.mkdirs();
            if (!b) {
                logger.info("新建目录失败");
                return fDir.getPath();
            }
        }

        dir.append("/ampie_data_");
        dir.append(StringUtil.getDateFormat(date, "ddHH"));
        dir.append(".xml");
        //文件按月份存放文件夹，按日期小时格式存放文件
        String filePath = dir.toString();
        File file = new File(filePath);
        if (file.exists()) {
            logger.info("已有此文件" + file.getPath());
            return filePath;
        } else {
            try {
                boolean b = file.createNewFile();
                if (!b) {
                    logger.info("新建文件失败");
                    return filePath;
                }
            } catch (IOException ex) {
                logger.info("新建文件错误： " + ex);
                return filePath;
            }
        }

        //没有则生成DataFile
        //项目任务量 = 所有已完成任务的花费天数 / 组员人数
        List<A1300PieVo> vos = new ArrayList<A1300PieVo>();
        List<Project> prjs = projectDao.findAll(-1, 1);//排除站务服务区
        for (Project p : prjs) {
            int costDays = 0;
            int developersCnt = userDao.findByProjectId(p.getId()).size();
            int sumMiss = missionDao.Count4Chart(p.getId());
            for (int i = 0; i < sumMiss; i += 20) {
                List<Mission> missions = missionDao.find4ChartByProjectId(p.getId(), 20, i);
                for (Mission m : missions) {
                    long l = m.getSubmitInfo().getSubmitDate().getTime() - m.getCreatetime().getTime();
                    costDays += l / (24 * 60 * 60 * 1000);
                }
            }
            A1300PieVo vo = new A1300PieVo();
            vo.setTitle(p.getName());
            vo.setDescription(p.getSummary());
            vo.setValue(StringUtil.getDecimal(costDays / developersCnt, null));
            vos.add(vo);
        }
        if (vos.size() > 0) {
            vos.get(vos.size() - 1).setPull_out(true);
        }

        String fileContent = ChartUtil.getPieXmlData(vos);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            bw.write(fileContent);
            bw.close();
        } catch (Exception ex) {
            logger.info("新建文件错误： " + ex);
            return filePath;
        }

        return filePath;
    }

    @Override
    public String getColumnDataFilePath() throws PdmsException {

        //判断是否已有
        StringBuffer dir = null;
        if (StartUpConfig.REALPATH != null) {
            dir = new StringBuffer(StartUpConfig.REALPATH.replace("\\", "/")).append("amcharts/amcolumn/data/");
        } else {
            dir = new StringBuffer("C:/pdms/amcharts/amcolumn/data/");
        }

        File fDir = new File(dir.toString());
        if (!fDir.exists()) {
            logger.info("新建目录" + fDir.getPath());
            boolean b = fDir.mkdirs();
            if (!b) {
                logger.info("新建目录失败");
                return fDir.getPath();
            }
        }

        Date date = new Date();
        dir.append("amcolumn_data_");
        dir.append(StringUtil.getDateFormat(date, "yyyyMM"));
        dir.append(".xml");
        //文件按年月格式存放文件
        String filePath = dir.toString();
        File file = new File(filePath);
        if (file.exists()) {
            logger.info("已有此文件" + file.getPath());
            return filePath;
        } else {
            try {
                boolean b = file.createNewFile();
                if (!b) {
                    logger.info("新建文件失败");
                    return filePath;
                }
            } catch (IOException ex) {
                logger.info("新建文件错误： " + ex);
                return filePath;
            }
        }

        //没有则生成DataFile
        A1301ColumnVo vo = new A1301ColumnVo();
        List<A1302GAndSVo> series = new ArrayList<A1302GAndSVo>();
        List<A1302GAndSVo> graphComplete = new ArrayList<A1302GAndSVo>();
        List<A1302GAndSVo> graphAll = new ArrayList<A1302GAndSVo>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.add(Calendar.MONTH, -12);
        for (int i = 0; i < 12; i++) {
            Date start = cal.getTime();
            cal.add(Calendar.MONTH, 1);
            Date end = cal.getTime();

            int missionAll = missionDao.CountAllByTime4Chart(start, end);
            System.out.println(missionAll);
            int missionComplete = missionDao.CountByTime4Chart(start, end);
            System.out.println(missionComplete);

            A1302GAndSVo voTemp = new A1302GAndSVo();
            voTemp.setValue(StringUtil.getDateFormat(start, "yy年M月"));
            voTemp.setXid(Integer.toString(i));
            series.add(voTemp);

            voTemp = new A1302GAndSVo();
            voTemp.setXid(Integer.toString(i));
            voTemp.setValue(Integer.toString(missionAll));
            graphAll.add(voTemp);

            voTemp = new A1302GAndSVo();
            voTemp.setXid(Integer.toString(i));
            voTemp.setValue(Integer.toString(missionComplete));
            graphComplete.add(voTemp);
        }
        vo.setSVo(series);
        List<List<A1302GAndSVo>> gVos = new ArrayList<List<A1302GAndSVo>>();
        gVos.add(graphAll);
        gVos.add(graphComplete);
        vo.setGVos(gVos);

        String fileContent = ChartUtil.getColumnXmlData(vo);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            bw.write(fileContent);
            bw.close();
        } catch (Exception ex) {
            logger.info("新建文件错误： " + ex);
            return filePath;
        }

        return filePath;
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
