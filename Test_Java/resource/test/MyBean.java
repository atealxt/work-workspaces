package test;

import java.io.Serializable;
import java.util.Date;

import org.apache.log4j.Logger;

public class MyBean implements Serializable {

    private static final long serialVersionUID = 1L;

    public MyBean() {
        super();
    }

    public Integer a(final Integer i) {
        logger.debug("a " + i);
        return i;
    }

    @SuppressWarnings("unused")
    private void b(final int i) {
        logger.debug("b " + i);
    }

    private int ccc;
    private String ddd;

    public int getCcc() {
        return ccc;
    }

    public void setCcc(final int ccc) {
        this.ccc = ccc;
    }

    public String getDdd() {
        logger.debug("getDdd");
        return ddd;
    }

    public String getEeeee() {
        return "asddsa";
    }

    public void setDdd(final String ddd) {
        logger.debug("setDdd: " + ddd);
        this.ddd = ddd;
    }

    @SuppressWarnings("unused")
    private int testPrivate(final String a, final int b) {
        logger.debug("a: " + a + ", b: " + b);
        return 100;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyBean [ccc=" + ccc + ", ddd=" + ddd + "]";
    }

    private static final Logger logger = Logger.getLogger(MyBean.class);
}
