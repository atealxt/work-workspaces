package pdms.components.vo;

import java.util.List;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0800UserVo {

    /** 我的最新主题 */
    private List<A0201TopicVo> ltTopicVo;
    /** 我的最新回复 */
    private List<A0201TopicVo> lrTopicVo;
    /** 用户ID */
    private String usrId;
    /** 用户名 */
    private String usrName;
    /** 用户身份 */
    private String usrIdent;
    /** 所属项目 */
    private String fPrj;

    public String getFPrj() {
        return fPrj;
    }

    public void setFPrj(String fPrj) {
        this.fPrj = fPrj;
    }

    public List<A0201TopicVo> getLrTopicVo() {
        return lrTopicVo;
    }

    public void setLrTopicVo(List<A0201TopicVo> lrTopicVo) {
        this.lrTopicVo = lrTopicVo;
    }

    public List<A0201TopicVo> getLtTopicVo() {
        return ltTopicVo;
    }

    public void setLtTopicVo(List<A0201TopicVo> ltTopicVo) {
        this.ltTopicVo = ltTopicVo;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrIdent() {
        return usrIdent;
    }

    public void setUsrIdent(String usrIdent) {
        this.usrIdent = usrIdent;
    }

    public String getUsrName() {
        return usrName;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }
}
