package pdms.components.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 * @author LUSuo(atealxt@gmail.com)
 */
public class User {

    /** 用户ID */
    protected int id;
    /** 登录用户名 */
    protected String loginid;
    /** 真实用户名 */
    protected String name;
    /** 密码 */
    protected String password;
    /** 公司IP */
    protected String ip;
    /** 状态 1在职 -1离职 0其他 */
    protected Integer status;
//    /** 所属项目(组) */
//    protected Set<Project> projects = new HashSet<Project>(0);
    /** 角色 */
    protected Set<Identity> identities = new HashSet<Identity>(0);
    /** 权限 */
    protected Set<Role> roles = new HashSet<Role>(0);
    /** 用户组 */
    protected Set<Group> groups = new HashSet<Group>(0);
//    /** 发帖 */
//    protected Set<Topic> topics = new HashSet<Topic>(0);
//    /** 回帖 */
//    protected Set<Reply> replys = new HashSet<Reply>(0);

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" 用户ID: ").append(getId());
        sb.append(" 登录用户名: ").append(getLoginid());
        sb.append(" 真实用户名: ").append(getName());
        sb.append(" 公司IP: ").append(getIp());
        sb.append(" 状态: ").append(getStatus());
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        final User u = (User) obj;
        if (u.getId() != getId() ||
            !u.getLoginid().equals(u.getLoginid())) {
            //only judge the primary key is well.
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + (this.loginid != null ? this.loginid.hashCode() : 0);
        return hash;
    }

//    public Set<Reply> getReplys() {
//        return replys;
//    }
//
//    public void setReplys(Set<Reply> replys) {
//        this.replys = replys;
//    }
//
//    public Set<Topic> getTopics() {
//        return topics;
//    }
//
//    public void setTopics(Set<Topic> topics) {
//        this.topics = topics;
//    }
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(Set<Identity> identities) {
        this.identities = identities;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

//    public Set<Project> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(Set<Project> projects) {
//        this.projects = projects;
//    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }
}
