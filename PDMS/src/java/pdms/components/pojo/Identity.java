package pdms.components.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色:<br>
 * PDMS<br>
 * 系统管理员<br>
 * 职员<br>
 * 项目负责人<br>
 * @author LUSuo(atealxt@gmail.com)
 */
public class Identity {

    protected int id;
    protected String name;
    /** 权限 */
    protected Set<Role> roles = new HashSet<Role>(0);
    /** 用户 */
    protected Set<User> users = new HashSet<User>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" id: ").append(getId());
        sb.append(" name: ").append(getName());
        return sb.toString();
    }
}
