package pdms.platform.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.components.dao.FileDao;
import pdms.components.dao.MissionDao;
import pdms.components.dao.UserDao;
import pdms.components.pojo.Mission;
import pdms.components.pojo.MissionSubmit;
import pdms.components.pojo.UploadFile;
import pdms.components.pojo.User;
import pdms.components.vo.A0900FileVo;
import pdms.platform.configeration.StartUpConfig;
import pdms.platform.constant.CommonConstant;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0900FileService;
import pdms.platform.util.RoleUtil;
import pdms.platform.util.StringUtil;
import static pdms.platform.constant.CommonConstant.DB_STATUS_NG;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0900FileServiceImpl implements A0900FileService {

    private static Log logger = LogFactory.getLog(A0900FileServiceImpl.class);
    private static String UPLOAD_FILE_DIRECTORY = "C:/pdms/upload/";//留作web服务未启动时的临时目录
    private FileDao fileDao;
    private UserDao userDao;
    private MissionDao missionDao;
    private boolean isManager;

    public A0900FileServiceImpl() {
        if (StartUpConfig.REALPATH != null) {
            UPLOAD_FILE_DIRECTORY = StartUpConfig.REALPATH.replace("\\", "/") + "upload/";
        }
    }

    @Override
    public List<A0900FileVo> MakeVo(String loginId, int maxNum, int startNum, String mode) throws PdmsException {

        List<A0900FileVo> ret = new ArrayList<A0900FileVo>();

        //List<UploadFile> uploadFiles = fileDao.findByUserLoginId(loginId, maxNum, startNum);
        List<UploadFile> uploadFiles = null;
        if ("m".equals(mode)) {
            User user = userDao.findByLoginId(loginId);
            String rule = RoleUtil.getOperate("File", user);
            if (!rule.contains("C") || !rule.contains("D")) {
                logger.warn("无此权限");
                return ret;
            }
            uploadFiles = fileDao.find(maxNum, startNum);
        } else {
            uploadFiles = fileDao.findByUserLoginId(loginId, maxNum, startNum);
        }
        for (UploadFile uf : uploadFiles) {
            A0900FileVo vo = new A0900FileVo();
            vo.setFId(uf.getId());
            vo.setFName(uf.getFileName());
            vo.setFAddr(uf.getAddress());
            vo.setFDate(StringUtil.getDateFormat(uf.getUploadDate(), "yyyy/MM/dd HH:mm:ss"));
            vo.setFType(getFileTypeShow(uf.getAddress()));
            vo.setFSize(getFileSize(uf.getAddress()));
            ret.add(vo);
        }
        //logger.info(ret.size());
        return ret;
    }

    @Override
    public int getSumCount(String loginId, String mode) throws PdmsException {

        if ("m".equals(mode)) {
            if (!isManager) {
                return fileDao.find(-1, 0).size();
            } else {
                return 0;
            }
        } else {
            return fileDao.findAllByUserLoginId(loginId).size();
        }
    }

    /** 取得文件的真实保存地址 */
    public static String getFileSaveAdd(User user, String type) throws PdmsException {
        if (user == null || type == null || type.length() == 0) {
            logger.error(user);
            logger.error(type);
            throw new PdmsException("InParam Error!");
        }
        StringBuffer sb = new StringBuffer();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
//        SimpleDateFormat sdf_directory = new SimpleDateFormat("yyyyMM");
        Date d = new Date();
//        sb.append(sdf_directory.format(d));
        sb.append(StringUtil.getDateFormat(d, "yyyyMM"));
        sb.append("/");
        sb.append(user.getLoginid());
        sb.append("_");
//        sb.append(sdf.format(d));
        sb.append(StringUtil.getDateFormat(d, "yyyyMMddHHmmssS"));
        sb.append(".");
        sb.append(type);

        return sb.toString();
    }

    /**
     * 取得文件类型
     * @param url 包含后辍名的文件目录
     * @return 文件类型的表示名称
     */
    public static String getFileTypeShow(String url) throws PdmsException {

        if (url == null || url.length() == 0) {
            throw new PdmsException("InParam Error!");
        }

        if (url.indexOf(".") != -1) {
            url = url.substring(url.lastIndexOf(".") + 1);
        }

        if ("xls".equalsIgnoreCase(url)) {
            return "MS EXCEL";
        }
        if ("txt".equalsIgnoreCase(url)) {
            return "MS文本文件";
        }
        if ("rar".equalsIgnoreCase(url) ||
            "zip".equalsIgnoreCase(url) ||
            "gz".equalsIgnoreCase(url) ||
            "7z".equalsIgnoreCase(url)) {
            return "压缩文件";
        }
        if ("html".equalsIgnoreCase(url) ||
            "htm".equalsIgnoreCase(url)) {
            return "网页";
        }
        if ("gif".equalsIgnoreCase(url) ||
            "jpg".equalsIgnoreCase(url) ||
            "png".equalsIgnoreCase(url) ||
            "bmp".equalsIgnoreCase(url)) {
            return "图片";
        }

        logger.info("文件类型无法识别： " + url);
        //return " - ";
        return url;
    }

    /**
     * 取得文件大小
     * @param url 包含后辍名的文件目录
     * @return 文件大小字节数
     */
    public static String getFileSize(String url) throws PdmsException {

        if (url == null || url.length() == 0) {
            throw new PdmsException("InParam Error!");
        }
        File f = new File(UPLOAD_FILE_DIRECTORY + url);
        if (!f.exists()) {
            logger.warn("File not found: " + f.getPath());
            return " - ";
        }

        float l = f.length();
        String decimal = null;
        if (l < 1000 * 1000) {
            decimal = StringUtil.getDecimal(l / 1000, "#,##0.0k");            
        } else {
            decimal = StringUtil.getDecimal(l / (1000 * 1000), "#,##0.0M");
        }
        if ("0.0k".equals(decimal)) {
            return "0.1k";
        }
        return decimal;
    }

    @Override
    public boolean delFile(List<String> list, String loginId) throws PdmsException {

        User user = userDao.findByLoginId(loginId);
        String rule = RoleUtil.getOperate("File", user);
        if (!rule.contains("U")) {
            logger.error("File权限认证失败");
            return false;
        }
        for (String fileId : list) {

            Mission mis = missionDao.findBySubmitFileId(fileId);
            if (mis != null) {
                logger.warn("此文件作为了任务提交附件，不能完全删除。File id: " + fileId);
                UploadFile uf = mis.getSubmitInfo().getUploadFile();
                uf.setDel(true);
                int updStatus = fileDao.Update(uf);
                if (updStatus == CommonConstant.DB_STATUS_NG) {
                    logger.error("更新文件状态失败");
                    return false;
                }
            } else {
                try {
                    String addr = fileDao.delFile(fileId);
                    if (addr == null) {
                        logger.warn("文件记录不存在");
                        return false;
                    }
                    addr = UPLOAD_FILE_DIRECTORY + addr;
                    logger.info("Delete File address: " + addr);
                    File f = new File(addr);
                    if (f.exists()) {
                        f.delete();
                    } else {
                        logger.warn("File not found: " + addr);
                    }
                } catch (Exception ex) {
                    logger.error(ex);
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String createFile(String loginId, String fName, String fNameAlias, File file) throws PdmsException {

        User user = userDao.findByLoginId(loginId);
        String rule = RoleUtil.getOperate("File", user);
        //if (!rule.contains("C")) {
        if (!rule.contains("U")) {//C和D为文件管理权限
            logger.error("File权限认证失败");
            return "{success:false,errors:'无文件上传权限！'}";
        }

        UploadFile uf = new UploadFile();
        uf.setUser(user);
        uf.setAddress(getFileSaveAdd(user, fName.substring(fName.lastIndexOf(".") + 1)));
        uf.setFileName(fNameAlias);
        uf.setUploadDate(new Date());

        //save to db
        int insertStatus = fileDao.Insert(uf);
        if (insertStatus == DB_STATUS_NG) {
            logger.error("上传失败！");
            return "{success:false,errors:'上传失败！'}";
        }

        //save to disk
        String sSaveDisk = UPLOAD_FILE_DIRECTORY + uf.getAddress();
        String dir = sSaveDisk.substring(0, sSaveDisk.lastIndexOf("/") + 1);
        File fDir = new File(dir);
        if (!fDir.exists()) {
            logger.info("新建上传目录" + fDir.getPath());
            fDir.mkdir();
        }
        boolean bSaveDisk = file.renameTo(new File(sSaveDisk));
        if (!bSaveDisk) {
            logger.error("保存文件失败！将删除相对应的DB记录。");
            fileDao.delFile(uf.getId());
            return "{success:false,errors:'保存文件失败！'}";
        }
        return "{success:true}";
    }

    @Override
    public String getFilePathByMissionId(String mId) {

        Mission mission = missionDao.findById(Integer.parseInt(mId));
        if (mission == null) {
            logger.error("Mission Id错误");
            return null;
        }

        MissionSubmit ms = mission.getSubmitInfo();
        if (ms == null) {
            logger.error("Mission未提交");
            return null;
        }

        UploadFile uf = ms.getUploadFile();
        if (uf == null) {
            logger.error("Mission无附件");
            return null;
        }

        String sSaveDisk = UPLOAD_FILE_DIRECTORY + uf.getAddress();
        File f = new File(sSaveDisk);
        if (!f.exists()) {
            return null;
        }
        //return sSaveDisk;
        return uf.getAddress();
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

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
