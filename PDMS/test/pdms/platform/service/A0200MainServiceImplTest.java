package pdms.platform.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.components.vo.A0200MainVo;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0200MainServiceImplTest {

    public A0200MainServiceImplTest() {
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
     * Test of MakeTreeInfo method, of class A0200MainServiceImpl.
     */
    @Test
    public void testMakeTreeInfo() {
        System.out.println("MakeTreeInfo");
        String loginId = "atea";
        String nodeId = "root";
        A0200MainService instance = (A0200MainService) DIManager.getBean("A0200MainService");
        String result = instance.MakeTreeInfo(loginId, nodeId);
        System.out.println(result);
    }

    /**
     * Test of MakeVo method, of class A0200MainServiceImpl.
     */
    @Test
    public void testMakeVo() throws Exception {
        System.out.println("MakeVo");
        String loginId = "atea";
        A0200MainService instance = (A0200MainService) DIManager.getBean("A0200MainService");
        A0200MainVo result = instance.MakeVo(loginId);
        System.out.println(result.getProjectVo().get(0).getMenberCnt());
    }
}