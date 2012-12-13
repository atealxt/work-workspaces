package pdms.platform.service;

import pdms.platform.service.impl.A1100SearchServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.vo.A1100SearchVo;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A1100SearchServiceImplTest {

    public A1100SearchServiceImplTest() {
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
     * Test of MakeVo method, of class A1100SearchServiceImpl.
     */
    @Test
    public void testMakeVo() throws Exception {
        System.out.println("MakeVo");
        int maxNum = 10;
        int startNum = 0;
        String loginId = "atea";

        String condition;
        condition = "s11s21s31ca<br>s11s21s32cT<br>s11s21s33c1<br>s11s21s34ce<br>";
        condition = "s11s22s31ca<br>s11s22s32cT<br>s11s22s33cs<br>";
        condition = "s11s21s31c<br>";
        condition = "s12s21s32c<br>";
        condition = "s12s22s31ca<br>s12s22s32cT<br>s12s22s33c1<br>";
        condition = "s13s21s32c<br>s13s22s32c<br>s13s23s31ca<br>s13s23s32cT<br>s13s24s31cw<br>s13s24s32c王<br>s13s26s31c6<br>s13s26s32c4<br>";
        condition = "s14s21s31ca<br>s14s21s32cT<br>";
        condition = "s14s22s31c2<br>s14s22s32c图<br>s14s22s33c使<br>";

        A1100SearchServiceImpl instance = (A1100SearchServiceImpl) DIManager.getBean("A1100SearchService");
        A1100SearchVo result = instance.MakeVo(maxNum, startNum, loginId, condition);
        assertNotNull(result);

        System.out.println("result.getResults: " + result.getResults());
        System.out.println("result.getHeaders: " + result.getHeaders());
        System.out.println("result.getData: " + result.getData());

    }
}