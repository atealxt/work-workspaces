package pdms.components.pojo;

import java.sql.Clob;
import java.util.Date;

/**
 * 主题帖
 * @author LUSuo(atealxt@gmail.com)
 */
public class Topic {

    /** 主题ID */
    protected String id;
    /** 主题名 */
    protected String name;
    /** 主题内容 */
    protected Clob content;
    /** 所在专区 */
    protected Project project;
    /** 发表时间 */
    protected Date createtime;
    /** 发表人(会员) */
    protected User createuser;
    /** 状态 1:开放 0:关闭 -1:删除 */
    protected Integer status;
    /** 分类 1:private 0:public */
    protected Integer topictype;
    /** 等级(Position) 1:Position1 2:Position2 */
    protected Integer topiclevel;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" 主题ID: ").append(getId());
        sb.append(" 主题名: ").append(getName());
        sb.append(" 发表时间: ").append(getCreatetime());
        sb.append(" 状态: ").append(getStatus());
        sb.append(" 分类: ").append(getTopictype());
        sb.append(" 等级: ").append(getTopiclevel());
        return sb.toString();
    }

    public Clob getContent() {
        return content;
    }

    public void setContent(Clob content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public User getCreateuser() {
        return createuser;
    }

    public void setCreateuser(User createuser) {
        this.createuser = createuser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /** 主题名 */
    public String getName() {
        return name;
    }

    /** 主题名 */
    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    /** 状态 1:开放 0:关闭 -1:删除 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTopiclevel() {
        return topiclevel;
    }

    /** 等级(Position) 1:Position1 2:Position2 */
    public void setTopiclevel(Integer topiclevel) {
        this.topiclevel = topiclevel;
    }

    public Integer getTopictype() {
        return topictype;
    }

    /** 分类 1:private 0:public */
    public void setTopictype(Integer topictype) {
        this.topictype = topictype;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
