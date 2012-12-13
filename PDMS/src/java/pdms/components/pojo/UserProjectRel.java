package pdms.components.pojo;

/**
 * 用户项目关系表
 * @author LUSuo(atealxt@gmail.com)
 */
public class UserProjectRel {

    protected String id;
    /** 是否可管理 */
    protected boolean canManage;
    protected User user;
    protected Project project;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" UserProjectRel ID: ").append(getId());
        sb.append(" 是否可管理: ").append(isCanManage());
        return sb.toString();
    }

    /** 是否可管理 */
    public boolean isCanManage() {
        return canManage;
    }

    /** 是否可管理 */
    public void setCanManage(boolean canManage) {
        this.canManage = canManage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
