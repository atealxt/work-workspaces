package pdms.components.pojo;

import java.io.Serializable;
import java.sql.Date;

/**
 * 任务
 * @author LUSuo(atealxt@gmail.com)
 */
public class Mission implements Serializable {

    /** 任务ID */
    protected int id;
//    /** 修改的历史记录(默认0)，随修改次数递增  */
//    protected int modified;
    /** 任务内容 */
    protected String content;
    /** 内容摘要 do not managed by hibernate */
    protected String summary;
    /** 任务建立时间 */
    protected Date createtime;
    /** 完成日限 */
    protected Date completetimeLimit;
    /** 确认截至时间 */
    protected Date confirmtimeLimit;
    /** 分配状态 do not managed by hibernate */
    protected boolean distribution;
    /** 分配人(会员) */
    protected User distributor;
    /** 接收人(会员) */
    protected User receiver;
    /** 分配确认状态(受取状态) 1:未确认 0:已确认 -1:确认失败  */
    protected Integer distributionConfirm;
    /** 完成状态 false:未完成 true:已完成  */
    protected boolean completeStatus;
    /** 验收确认状态 1:未验收 0:已验收 -1:验收未通过 -2:再次提交请求验收 */
    protected Integer inspectStatus;
    /** 验收人(会员) */
    protected User inspector;
    /** 提交信息 */
    protected MissionSubmit submitInfo;
    /** 所属项目 */
    protected Project project;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" 任务ID: ").append(getId());
        sb.append(" 内容摘要: ").append(getSummary());
        sb.append(" 任务建立时间: ").append(getCreatetime());
        sb.append(" 完成日限: ").append(getCompletetimeLimit());
        sb.append(" 确认截至时间: ").append(getConfirmtimeLimit());
        sb.append(" 分配状态: ").append(isDistribution());
        sb.append(" 分配确认状态: ").append(getDistributionConfirm());
        sb.append(" 完成状态: ").append(isCompleteStatus());
        sb.append(" 验收确认状态: ").append(getInspectStatus());
        return sb.toString();
    }

    public boolean isDistribution() {
        return !(receiver == null);
    }

    public String getSummary() {
        if (summary != null) {
            return summary;
        }
        if (content == null || content.length() < 20) {
            summary = content;
        } else {
            summary = content.substring(0, 20);
        }
        return summary;
    }

    public User getDistributor() {
        return distributor;
    }

    public void setDistributor(User distributor) {
        this.distributor = distributor;
    }

    public boolean isCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(boolean completeStatus) {
        this.completeStatus = completeStatus;
    }

    public Date getCompletetimeLimit() {
        return completetimeLimit;
    }

    public void setCompletetimeLimit(Date completetimeLimit) {
        this.completetimeLimit = completetimeLimit;
    }

    public Date getConfirmtimeLimit() {
        return confirmtimeLimit;
    }

    public void setConfirmtimeLimit(Date confirmtimeLimit) {
        this.confirmtimeLimit = confirmtimeLimit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getDistributionConfirm() {
        return distributionConfirm;
    }

    public void setDistributionConfirm(Integer distributionConfirm) {
        this.distributionConfirm = distributionConfirm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getInspectStatus() {
        return inspectStatus;
    }

    public void setInspectStatus(Integer inspectStatus) {
        this.inspectStatus = inspectStatus;
    }

    public User getInspector() {
        return inspector;
    }

    public void setInspector(User inspector) {
        this.inspector = inspector;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public MissionSubmit getSubmitInfo() {
        return submitInfo;
    }

    public void setSubmitInfo(MissionSubmit submitInfo) {
        this.submitInfo = submitInfo;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    //    public int getModified() {
//        return modified;
//    }
//
//    public void setModified(int modified) {
//        this.modified = modified;
//    }
}
