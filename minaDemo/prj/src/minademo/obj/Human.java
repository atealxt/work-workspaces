package minademo.obj;

import java.util.Date;
import java.util.List;

public class Human {

    private long id;
    private String name;
    private int age;
    private Date now;
    private byte[] data;

    private List<Language> languages;

    public Human(final long id, final String name, final int age) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        now = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(final byte[] data) {
        this.data = data;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(final List<Language> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "Human [id=" + id + ", name=" + name + ", age=" + age + ", now=" + now + //
                ", data=" + new String(data) + //
                ", languages=" + languages + "]";
    }

}
