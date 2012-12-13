package pdms.components.pojo;

import java.sql.Date;

/**
 * 项目(专区)
 * @author LUSuo(atealxt@gmail.com)
 */
public class Project {

    /** 项目ID */
    protected int id;
    /** 项目名 */
    protected String name;
    /** 项目简要 */
    protected String summary;
    /** LOGO */
    protected String logo;
    /** 公告 */
    protected String announcement;
    /** 状态 1开放 -1关闭 0其他 */
    protected Integer status;
    /** 开发开始日 */
    protected Date starttime;
    /** 开发终了日 */
    protected Date endtime;
//    /** 项目负责人(会员) */
//    protected Set<User> managers = new HashSet<User>(0);
//    /** 组员(会员) */
//    protected Set<User> users = new HashSet<User>(0);

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" 项目ID: ").append(getId());
        sb.append(" 项目名: ").append(getName());
        sb.append(" 项目简要: ").append(getSummary());
        sb.append(" LOGO: ").append(getLogo());
        sb.append(" 公告: ").append(getAnnouncement());
        sb.append(" 状态: ").append(getStatus());
        sb.append(" 开发开始日: ").append(getStarttime());
        sb.append(" 开发终了日: ").append(getEndtime());
        return sb.toString();
    }

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnouncement() {
        return announcement;
    }

    /** 公告 */
    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

//    public Set<User> getManagers() {
//        return managers;
//    }
//
//    public void setManagers(Set<User> managers) {
//        this.managers = managers;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Integer getStatus() {
        return status;
    }

    /** 状态 1开放 -1关闭 0其他 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
