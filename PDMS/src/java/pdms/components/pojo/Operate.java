package pdms.components.pojo;

/**
 * 操作:<br>
 * CRUD<br>
 * CRU<br>
 * RU<br>
 * R<br>
 * D<br>
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class Operate {

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
