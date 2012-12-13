package pdms.platform.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.components.vo.A0900FileVo;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0900FileServiceImplTest {

    public A0900FileServiceImplTest() {
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
     * Test of MakeVo method, of class A0900FileServiceImpl.
     */
    @Test
    public void testMakeVo() throws Exception {
        System.out.println("MakeVo");
        String loginId = "atea";
        int maxNum = 2;
        A0900FileService instance = (A0900FileService) DIManager.getBean("A0900FileService");
        List<A0900FileVo> result = instance.MakeVo(loginId, maxNum, 1, null);

        System.out.println(result.get(0).getFType() + result.get(0).getFSize());
    }

    @Test
    public void testDelFile() throws Exception {
        System.out.println("DelFile");
        A0900FileService instance = (A0900FileService) DIManager.getBean("A0900FileService");

        List list = new ArrayList();
        list.add("2c90e2411f4a63d0011f4a63ea020014");
        System.out.println(instance.delFile(list, "atea"));

    }
}