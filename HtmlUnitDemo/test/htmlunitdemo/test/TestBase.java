package htmlunitdemo.test;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public abstract class TestBase {

    protected Logger logger = Logger.getLogger(getClass());
    private long costTime = System.currentTimeMillis();

    @Before
    public void setUp() {
        logger.debug("test start");
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
            e.printStackTrace();
        }
    }

    public abstract void excute() throws Exception;
}
