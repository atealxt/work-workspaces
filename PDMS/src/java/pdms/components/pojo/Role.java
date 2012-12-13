package pdms.components.pojo;

/**
 * 权限
 * @author LUSuo(atealxt@gmail.com)
 */
public class Role {

    protected int id;
    protected String name;
    /** 功能模块 */
    protected Function function;
    /** 操作 */
    protected Operate operate;

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Operate getOperate() {
        return operate;
    }

    public void setOperate(Operate operate) {
        this.operate = operate;
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
