package pdms.components.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.pojo.Identity;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class IdentityDaoImplTest {

    public IdentityDaoImplTest() {
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
     * Test of selectAll method, of class IdentityDaoImpl.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        IdentityDao instance = (IdentityDao) DIManager.getBean("IdentityDao");
        List<Identity> result = instance.selectAll();
        assertTrue(result.size() > 0);
        System.out.println(result);
    }
}