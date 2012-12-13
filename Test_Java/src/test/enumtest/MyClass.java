package test.enumtest;

public class MyClass {

    public final static MyClass ENUM1 = new MyClass(1);
    public final static MyClass ENUM2 = new MyClass(2);

    private MyClass(final int status) {
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return status;
    }
}
