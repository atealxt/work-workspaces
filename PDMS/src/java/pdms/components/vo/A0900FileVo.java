package pdms.components.vo;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0900FileVo {

    /** 文件ID */
    private String fId;
    /** 文件名 */
    private String fName;
    /** 类型 */
    private String fType;
    /** 地址 */
    private String fAddr;
    /** 大小 */
    private String fSize;
    /** 上传日期 */
    //private Date fDate;
    private String fDate;

    public String getFId() {
        return fId;
    }

    public void setFId(String fId) {
        this.fId = fId;
    }

    public String getFAddr() {
        return fAddr;
    }

    public void setFAddr(String fAddr) {
        this.fAddr = fAddr;
    }

    public String getFDate() {
        return fDate;
    }

    public void setFDate(String fDate) {
        this.fDate = fDate;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getFSize() {
        return fSize;
    }

    public void setFSize(String fSize) {
        this.fSize = fSize;
    }

    public String getFType() {
        return fType;
    }

    public void setFType(String fType) {
        this.fType = fType;
    }
}
