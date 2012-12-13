package easycache;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MyBean implements Serializable {

    private int a;
    private String b;
    private Float[] c;
    private double[][] d;

    public int getA() {
        return a;
    }

    public void setA(final int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(final String b) {
        this.b = b;
    }

    public Float[] getC() {
        return c;
    }

    public void setC(final Float[] c) {
        this.c = c;
    }

    public double[][] getD() {
        return d;
    }

    public void setD(final double[][] d) {
        this.d = d;
    }
}
