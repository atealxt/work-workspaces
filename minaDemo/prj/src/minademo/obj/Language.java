package minademo.obj;

public class Language {

    private String name;

    public Language(final String name) {
        super();
        this.name = name;
    }

    @Override
    public String toString() {
        return "Language [name=" + name + "]";
    }
}
