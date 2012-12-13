package pdms.components.dto;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1000MyMissionDto {

    private String pId;
    private String mId;
    private String t_content;
    private String completlimit;
    private String confirmlimit;

    public String getMId() {
        return mId;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public String getCompletlimit() {
        return completlimit;
    }

    public void setCompletlimit(String completlimit) {
        this.completlimit = completlimit;
    }

    public String getConfirmlimit() {
        return confirmlimit;
    }

    public void setConfirmlimit(String confirmlimit) {
        this.confirmlimit = confirmlimit;
    }

    public String getPId() {
        return pId;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public String getT_content() {
        return t_content;
    }

    public void setT_content(String t_content) {
        this.t_content = t_content;
    }
}
