package easydao.test;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class TestBase {

    protected Logger logger = Logger.getLogger(super.getClass());
    private long costTime;

    @Before
    public void setUp() {
        logger.debug("test start");
        costTime = System.currentTimeMillis();
    }

    @After
    public void tearDown() throws Exception {
        logger.debug("test end");
        BigDecimal time = new BigDecimal(System.currentTimeMillis() - costTime);
        time = time.divide(new BigDecimal(1000));
        logger.debug("cost time: " + time.doubleValue() + "s");
    }

    @Test
    public void test() {
        try {
            execute();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }

    public abstract void execute() throws Exception;
}
