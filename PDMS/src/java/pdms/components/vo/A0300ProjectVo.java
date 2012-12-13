package pdms.components.vo;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0300ProjectVo {

    /** 帖子id */
    private String TId;
    /** 帖子等级 */
    private String TLevel;
    /** 帖子名 */
    private String TName;
    /** 发帖人 */
    private String TUser;
    /** 回复人数 */
    private String TRCnt;
    /** 发表时间 */
    private String TDate;
    /** 帖子状态 */
    private String TStatus;

    public String getTStatus() {
        return TStatus;
    }

    public void setTStatus(String TStatus) {
        this.TStatus = TStatus;
    }

    public String getTDate() {
        return TDate;
    }

    public void setTDate(String TDate) {
        this.TDate = TDate;
    }

    public String getTId() {
        return TId;
    }

    public void setTId(String TId) {
        this.TId = TId;
    }

    public String getTLevel() {
        return TLevel;
    }

    public void setTLevel(String TLevel) {
        this.TLevel = TLevel;
    }

    public String getTName() {
        return TName;
    }

    public void setTName(String TName) {
        this.TName = TName;
    }

    public String getTRCnt() {
        return TRCnt;
    }

    public void setTRCnt(String TRCnt) {
        this.TRCnt = TRCnt;
    }

    public String getTUser() {
        return TUser;
    }

    public void setTUser(String TUser) {
        this.TUser = TUser;
    }
}
