package pdms.components.vo;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0400TopicVo {

    /** 回复帖id */
    private String RId;
    /** 回复帖Title */
    private String RTitle;
    /** 回复帖内容 */
    private String RContent;
    /** 回复人 */
    private String RUsr;
    /** 发表时间 */
    private String RTime;
    /**
     * 是主题帖还是回复帖<br>
     * true:主题帖 <br>
     * false:回复帖
     */
    private boolean IsTopic = false;

    public boolean isIsTopic() {
        return IsTopic;
    }

    public void setIsTopic(boolean IsTopic) {
        this.IsTopic = IsTopic;
    }

    public String getRContent() {
        return RContent;
    }

    public void setRContent(String RContent) {
        this.RContent = RContent;
    }

    public String getRId() {
        return RId;
    }

    public void setRId(String RId) {
        this.RId = RId;
    }

    public String getRTitle() {
        return RTitle;
    }

    public void setRTitle(String RTitle) {
        this.RTitle = RTitle;
    }

    public String getRUsr() {
        return RUsr;
    }

    public void setRUsr(String RUsr) {
        this.RUsr = RUsr;
    }

    public String getRTime() {
        return RTime;
    }

    public void setRTime(String RTime) {
        this.RTime = RTime;
    }
}
