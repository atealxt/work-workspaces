package pdms.platform.service;

import pdms.platform.service.impl.A1200GroupMServiceImpl;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.vo.A1200GroupMVo;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1200GroupMServiceImplTest {

    public A1200GroupMServiceImplTest() {
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
     * Test of MakeVo method, of class A1200GroupMServiceImpl.
     */
    @Test
    public void testMakeVo() throws Exception {
        System.out.println("MakeVo");
        int maxNum = 10;
        int startNum = 0;
        A1200GroupMServiceImpl instance = (A1200GroupMServiceImpl) DIManager.getBean("A1200GroupMService");
        List<A1200GroupMVo> result = instance.MakeVo(maxNum, startNum);
        assertTrue(result.size() > 0);
    }

    /**
     * Test of getSumCount method, of class A1200GroupMServiceImpl.
     */
    @Test
    public void testGetSumCount() throws Exception {
        System.out.println("getSumCount");
        A1200GroupMServiceImpl instance = (A1200GroupMServiceImpl) DIManager.getBean("A1200GroupMService");
        int result = instance.getSumCount();
        System.out.println(result);
    }
}