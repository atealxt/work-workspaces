package pdms.components.pojo;

import java.util.Date;

/**
 * 文件
 * @author LUSuo(atealxt@gmail.com)
 */
public class UploadFile {

    /** 文件ID */
    protected String id;
    /** 显示文件名 */
    protected String fileName;
    /** 真实地址(包括存储的文件名) */
    protected String address;
    /** 上传日期 */
    protected Date uploadDate;
    /** 上传者 */
    protected User user;
    /** 是否已删除 true是 false否 */
    protected boolean del;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" 文件ID: ").append(getId());
        sb.append(" 显示文件名: ").append(getFileName());
        sb.append(" 真实地址: ").append(getAddress());
        sb.append(" 上传日期: ").append(getUploadDate());
        sb.append(" 是否已删除: ").append(isDel());
        return sb.toString();
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
