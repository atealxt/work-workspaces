package pdms.components.pojo;

import java.sql.Clob;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pdms.platform.util.StringUtil;

/**
 * 回复帖
 * @author LUSuo(atealxt@gmail.com)
 */
public class Reply {

    private static Log logger = LogFactory.getLog(Reply.class);
    /** 回复帖ID */
    protected String id;
    /** 回复内容 */
    protected Clob content;
    /** 内容摘要 do not managed by hibernate */
    protected String summary;
    /** 发表时间 */
    protected Date createtime;
    /** 发表人(会员) */
    protected User createuser;
    /** 所属主题 */
    protected Topic topic;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" 回复帖ID: ").append(getId());
        sb.append(" 内容摘要: ").append(getSummary());
        sb.append(" 发表时间: ").append(getCreatetime());
        return sb.toString();
    }

    public String getSummary() {
        if (summary != null) {
            return summary;
        }
        if (content == null) {
            return null;
        }

        try {
//            if (content.length() < 50) {
//                int len = (int) content.length();
//                summary = content.getSubString(1, len);
//            } else {
//                summary = content.getSubString(1, 20);
//            }

            StringBuffer sb = new StringBuffer();
            sb.append(content.getSubString(1, (int) content.length()));
            String sTemp = StringUtil.delFtmlTag(sb.toString());
            int len = sTemp.length();
            if (len < 50) {
                summary = sTemp;
            } else {
                summary = sTemp.substring(0, len);
            }

        } catch (Exception ex) {
            logger.error(ex);
        }
        return summary;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
