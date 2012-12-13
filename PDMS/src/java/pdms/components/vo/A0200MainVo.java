package pdms.components.vo;

import java.util.List;

/**
 * 
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0200MainVo {

    /* 主题信息用cache存起来效果会更好 */
    /** 最新主题 */
    private List<A0201TopicVo> ltTopicVo;
    /** 最新回复 */
    private List<A0201TopicVo> lrTopicVo;
    /** 未完结主题 */
    private List<A0201TopicVo> luTopicVo;
    /** 各种专区(项目) */
    private List<A0202ProjectVo> projectVo;
    /** 用户名 */
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /** 最新回复 */
    public List<A0201TopicVo> getLrTopicVo() {
        return lrTopicVo;
    }

    /** 最新回复 */
    public void setLrTopicVo(List<A0201TopicVo> lrTopicVo) {
        this.lrTopicVo = lrTopicVo;
    }

    /** 最新主题 */
    public List<A0201TopicVo> getLtTopicVo() {
        return ltTopicVo;
    }

    /** 最新主题 */
    public void setLtTopicVo(List<A0201TopicVo> ltTopicVo) {
        this.ltTopicVo = ltTopicVo;
    }

    /** 未完结主题 */
    public List<A0201TopicVo> getLuTopicVo() {
        return luTopicVo;
    }

    /** 未完结主题 */
    public void setLuTopicVo(List<A0201TopicVo> luTopicVo) {
        this.luTopicVo = luTopicVo;
    }

    /** 各种专区(项目) */
    public List<A0202ProjectVo> getProjectVo() {
        return projectVo;
    }

    /** 各种专区(项目) */
    public void setProjectVo(List<A0202ProjectVo> projectVo) {
        this.projectVo = projectVo;
    }
}
