package pdms.platform.service;

import pdms.platform.service.impl.A1300ChartServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.platform.configeration.DIManager;
import pdms.platform.util.StringUtil;
import static org.junit.Assert.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1300ChartServiceImplTest {

    public A1300ChartServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPieDataFilePath method, of class A1300ChartServiceImpl.
     */
    @Test
    public void testGetPieDataFilePath() throws Exception {
        System.out.println("getPieDataFilePath");
        A1300ChartServiceImpl instance = (A1300ChartServiceImpl) DIManager.getBean("A1300ChartService");
        String result = instance.getPieDataFilePath();
        assertFalse(StringUtil.isEmpty(result));
        System.out.println(result);
    }

    /**
     * Test of getColumnDataFilePath method, of class A1300ChartServiceImpl.
     */
    @Test
    public void testGetColumnDataFilePath() throws Exception {
        System.out.println("getColumnDataFilePath");
        A1300ChartServiceImpl instance = (A1300ChartServiceImpl) DIManager.getBean("A1300ChartService");
        String result = instance.getColumnDataFilePath();
        assertFalse(StringUtil.isEmpty(result));
        System.out.println(result);
    }
}