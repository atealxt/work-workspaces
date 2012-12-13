package pdms.platform.configeration;

import java.util.List;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class DaoManagerTest {

    public DaoManagerTest() {
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
     * Test of getSession method, of class DaoManager.
     */
    @Test
    public void testGetSession() {
        Session session = DaoManager.getSession();
        session.beginTransaction();

        List list = session.createQuery("from Example").list();
        assertTrue(list.size() > 0);
        System.out.println(list.get(0));
    }
}