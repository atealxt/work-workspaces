package pdms.components.pojo;

import java.util.Date;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class MissionSubmit {

    /** ID */
    protected String id;
    /** 提交信息 */
    protected String submitInfo;
    /** 上传文件名(暂定只能上传一个文件) */
    //protected String uploadFileName;
    /** 上传文件内容 */
    //protected Blob fileContent;
    /** 任务 */
    protected Mission mission;
    /** 提交日期 */
    protected Date submitDate;
    /** 附件 */
    protected UploadFile uploadFile;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        //sb.append(" 任务ID ").append(getMid());
        //sb.append(" 提交信息: ").append(getSubmitInfo());
        //sb.append(" 上传文件名: ").append(getUploadFileName());
        return sb.toString();
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

//    public Blob getFileContent() {
//        return fileContent;
//    }
//
//    public void setFileContent(Blob fileContent) {
//        this.fileContent = fileContent;
//    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubmitInfo() {
        return submitInfo;
    }

    public void setSubmitInfo(String submitInfo) {
        this.submitInfo = submitInfo;
    }

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(UploadFile uploadFile) {
        this.uploadFile = uploadFile;
    }

//    public String getUploadFileName() {
//        return uploadFileName;
//    }
//
//    public void setUploadFileName(String uploadFileName) {
//        this.uploadFileName = uploadFileName;
//    }
}
