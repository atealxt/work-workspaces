package seleniumdemo;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.SeleneseTestCase;

/**
 * 1.转测试用例前需要java -jar selenium-server.jar<br>
 * 2.即使是junit4，selenium也需要方法test开头<br>
 */
public abstract class TestBase extends SeleneseTestCase {

    protected Logger logger = Logger.getLogger(super.getClass());

    public abstract String getUrl();

    public abstract boolean runInIE();

    public abstract boolean stopBrowserAfterTest();

    @Override
    @Before
    public void setUp() throws Exception {
        if (runInIE()) {
            setUp(getUrl(), "*iexplore");
        } else {
            setUp(getUrl(), "*firefox");
        }
    }

    @Test
    public void test() {
        try {
            execute();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public abstract void execute();

    @Override
    @After
    public void tearDown() throws Exception {
        if (stopBrowserAfterTest()) {
            selenium.stop();
        }
    }

}
