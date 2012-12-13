package test_visitor;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestVisitor {

    private long costTime;
    private static Logger logger = Logger.getLogger(TestVisitor.class);

    @Before
    public void setUp() {
        logger.debug("test start");
        costTime = System.currentTimeMillis();
    }

    @After
    public void tearDown() throws Exception {
        logger.debug("test end");
        logger.debug("cost time: " + (System.currentTimeMillis() - costTime) + "ms");
    }

    @Test
    public void test() {
        try {
            excute();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void excute() {

        Model1 m1 = new Model1();
        m1.accept(new VisitorModel1());
        Assert.assertEquals("100", m1.getAge());

        Model2 m2 = new Model2();
        m2.accept(new VisitorModel2());
        Assert.assertEquals("addr", m2.getAddress());

    }

    @Test
    public void classcastexception() {
        Model2 m = new Model2();
        m.accept(new VisitorModel1());
    }
}
