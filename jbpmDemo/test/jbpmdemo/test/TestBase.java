package jbpmdemo.test;

import org.apache.log4j.Logger;
import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;
import org.junit.After;
import org.junit.Before;

public abstract class TestBase {

    protected Logger logger = Logger.getLogger(getClass());
    private long costTime = System.currentTimeMillis();

    protected ProcessEngine processEngine;

    @Before
    public void setUp() {
        logger.debug("test start");
        processEngine = Configuration.getProcessEngine();
    }

    @After
    public void tearDown() throws Exception {
        logger.debug("test end");
        logger.debug("cost time: " + (System.currentTimeMillis() - costTime) + "ms");
    }

    public void test() {
        try {
            excute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void excute() throws Exception;
}
