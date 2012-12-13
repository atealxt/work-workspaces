package pdms.platform.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import pdms.components.vo.A0900FileVo;
import pdms.platform.core.EasyAction;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0900FileService;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0900FileAction extends EasyAction {

    private static Log logger = LogFactory.getLog(A0900FileAction.class);
    private A0900FileService fileService;
    /** file json data */
    private List<A0900FileVo> items;
    /** 记录集(名称待定..) */
    private long results;
    /**
     * 上传文件
     * 目前上传用的临时目录是Server的默认目录
     */
    private File file;
    private String fileName;
    /** 转到文件下载后页面所显示的信息(暂定) */
    private String downloadMessage;
    /** 下载的文件 */
    private String filePath;

    /**
     * main
     */
    @Override
    public String execute() throws PdmsException {

        String str_start = getParamValue("start");
        String str_limit = getParamValue("limit");
        String mode = getParamValue("mode");
        int start = 0;
        int limit = 10;
        if (str_start != null) {
            start = Integer.parseInt(getParamValue("start"));
        }
        if (str_limit != null) {
            limit = Integer.parseInt(getParamValue("limit"));
        }

        logger.info("start: " + start);
        logger.info("limit: " + limit);
        logger.info("mode: " + mode);

        //items = fileService.MakeVo((String) getSession().get("loginId"), 100, 0);
        String loginId = (String) getSession().get("loginId");
        items = fileService.MakeVo(loginId, limit, start, mode);
        results = fileService.getSumCount(loginId, mode);
        logger.info("results: " + results);

        cleanSess();
        return SUCCESS;
    }

    /**
     * 删除文件
     */
    public String delFile() throws PdmsException, IOException {

        String[] ids = getParamValue("ids").split("-");
        List idList = Arrays.asList(ids);
        boolean isSuccess = fileService.delFile(idList, (String) getSession().get("loginId"));
        getResponse().setContentType("text/json;charset=UTF-8");
        getResponse().getWriter().write("{success:" + isSuccess + "}");

        cleanSess();
        return null;
    }

    /**
     * 上传新文件
     */
    public String createFile() {

        getResponse().setContentType("text/html;charset=UTF-8");

        logger.info("fName:" + getParamValue("fName"));
        logger.info("file: " + getUF());
        String loginId = (String) getSession().get("loginId");
        String fNameAlias = getParamValue("fName");

        try {
            getResponse().getWriter().write(fileService.createFile(loginId, fileName, fNameAlias, file));
        } catch (Exception ex) {
            logger.error(ex);
            try {
                getResponse().getWriter().write("{success:false,errors:'上传发生异常'}");
            } catch (IOException ex1) {
                logger.error(ex1);
            }
        }

        cleanSess();
        return null;
    }

    /**
     * 下载文件
     */
    public String downloadFile() throws IOException {

        String ret = SUCCESS;
        String mId = getParamValue("mId");
        logger.info(mId);

        //search file
        if (!StringUtil.isEmpty(mId)) {
            String path = fileService.getFilePathByMissionId(mId);
            if (path != null) {
                filePath = path;
                ret = "findout";
            } else {
                //没找到
                downloadMessage = "文件不存在";
            }
        } else {
            //没找到
            downloadMessage = "文件不存在";
        }

        cleanSess();
        return ret;
    }

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public long getResults() {
        return results;
    }

    public void setResults(long results) {
        this.results = results;
    }

    public String getUFFileName() {
        return fileName;
    }

    public void setUFFileName(String fileName) {
        this.fileName = fileName;
    }

    public File getUF() {
        return file;
    }

    public void setUF(File file) {
        this.file = file;
    }

    public String getDownloadMessage() {
        return downloadMessage;
    }

    public void setDownloadMessage(String downloadMessage) {
        this.downloadMessage = downloadMessage;
    }

    public InputStream getInputStream() throws Exception {
        return ServletActionContext.getServletContext().getResourceAsStream("upload/" + filePath);
    }

    public String getFilePath() {
        if (filePath != null) {
            return filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return filePath;
    }

//    public A0900FileService getFileService() {
//        return fileService;
//    }

    public void setFileService(A0900FileService fileService) {
        this.fileService = fileService;
    }
}
