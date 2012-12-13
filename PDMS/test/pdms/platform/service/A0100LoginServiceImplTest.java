package pdms.platform.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.platform.configeration.DIManager;
import static org.junit.Assert.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class A0100LoginServiceImplTest {

    public A0100LoginServiceImplTest() {
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
     * Test of Login method, of class A0001LoginServiceImpl.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("Login");
        String loginId = "pdms";
        String psw = "pdms";
        A0100LoginService instance = (A0100LoginService)DIManager.getBean("A0100LoginService");
        boolean expResult = true;
        boolean result = instance.Login(loginId, psw);
        assertEquals(expResult, result);        
    }

}