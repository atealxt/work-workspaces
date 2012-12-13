package pdms.components.dao;

import pdms.components.dao.impl.ExampleDaoImpl;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.components.pojo.Example;
import pdms.platform.configeration.DIManager;
import static org.junit.Assert.*;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class ExampleDaoImplTest {

    public ExampleDaoImplTest() {
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
     * Test of findByName method, of class ExampleDaoImpl.
     */
    //@Test
    public void testFindByName() {
        System.out.println("findByName");
        ExampleDao dao = (ExampleDao) DIManager.getBean("ExampleDao");
        String name = "firstname";
        Example result = dao.findByName(name);
        assertNotNull(result);
        System.out.println(result);
    }

    /**
     * Test of getExamples method, of class ExampleDaoImpl.
     */
    @Test
    public void testGetExamples() {
        System.out.println("getExamples");
        ExampleDaoImpl instance = new ExampleDaoImpl();
        for (int i = 0; i < 5; i++) {
            List<Example> result = instance.getExamples();
            assertTrue(result.size() > 0);
        }


    }
}