package pdms.components.pojo;

/**
 * 功能模块:<br>
 * 个人信息Personal<br>
 * 用户User<br>
 * 主题帖Topic<br>
 * 回复帖Reply<br>
 * 任务Mission<br>
 * 项目Project<br>
 * 文件File(对于文件来说，U是个人文件管理权限，CD是管理网站所有文件权限)<br>
 * 报表统计Report<br>
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class Function {

    protected int id;
    protected String name;

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

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(" id: ").append(getId());
        sb.append(" name: ").append(getName());
        return sb.toString();
    }
}
