package pdms.platform.service;

import pdms.platform.service.impl.A1000MyMissionServiceImpl;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.vo.A1000MyMissionVo;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1000MyMissionServiceImplTest {

    public A1000MyMissionServiceImplTest() {
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
     * Test of MakeVo method, of class A1000MyMissionServiceImpl.
     */
    @Test
    public void testMakeVo() throws Exception {
        System.out.println("MakeVo");
        String loginId = "wangqiang";
        int maxNum = 3;
        int startNum = 0;
        A1000MyMissionServiceImpl instance = (A1000MyMissionServiceImpl) DIManager.getBean("A1000MyMissionService");
        List<A1000MyMissionVo> result = instance.MakeVo(loginId, maxNum, startNum, null);

        assertTrue(result.size() > 0);
        for (A1000MyMissionVo vo : result) {
            System.out.println(vo.getMComtime());
            System.out.println(vo.getMConfirm());
            System.out.println(vo.getMCs());
            System.out.println(vo.getMDc());
            System.out.println(vo.getMId());
            System.out.println(vo.getMIs());
            System.out.println(vo.getMName());
            System.out.println(vo.getMPrj());
        }
    }

    /**
     * Test of getSumCount method, of class A1000MyMissionServiceImpl.
     */
    @Test
    public void testGetSumCount() throws Exception {
        System.out.println("getSumCount");
        String loginId = "wangqiang";
        A1000MyMissionServiceImpl instance = (A1000MyMissionServiceImpl) DIManager.getBean("A1000MyMissionService");
        int result = instance.getSumCount(loginId, null);
        System.out.println(result);
    }
}