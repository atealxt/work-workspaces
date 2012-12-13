package pdms.components.dto;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0400TopicDto {

    private String pid;
    private String tName;
    private String tLevel;
    private String tRole;
    private String tTitle;
    private String tContent;
    private String loginId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTContent() {
        return tContent;
    }

    public void setTContent(String tContent) {
        this.tContent = tContent;
    }

    public String getTLevel() {
        return tLevel;
    }

    public void setTLevel(String tLevel) {
        this.tLevel = tLevel;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public String getTRole() {
        return tRole;
    }

    public void setTRole(String tRole) {
        this.tRole = tRole;
    }

    public String getTTitle() {
        return tTitle;
    }

    public void setTTitle(String tTitle) {
        this.tTitle = tTitle;
    }
}
