package entity;

public class MyBean {

    private int i;

    private String s;

    public int getI() {
        return i;
    }

    public void setI(final int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(final String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "MyBean [i=" + i + ", s=" + s + "]";
    }
}
