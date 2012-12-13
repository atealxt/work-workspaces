package pdms.platform.service;

import pdms.platform.service.impl.A0800UserServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.components.vo.A0800UserVo;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0800UserServiceImplTest {

    public A0800UserServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        DIManager.getBean("DBConfig");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of MakeVo method, of class A0800UserServiceImpl.
     */
    @Test
    public void testMakeVo() throws Exception {
        System.out.println("MakeVo");
        String loginId = "atea";
        A0800UserServiceImpl instance = (A0800UserServiceImpl)DIManager.getBean("A0800UserService");
        A0800UserVo result = instance.MakeVo(loginId);
        System.out.println(result.getUsrId());
        System.out.println(result.getUsrName());
        System.out.println(result.getUsrIdent());
        System.out.println(result.getFPrj());
        System.out.println(result.getLrTopicVo().size());
        System.out.println(result.getLtTopicVo().size());
    }

}