package pdms.components.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.pojo.Topic;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class TopicDaoImplTest {

    public TopicDaoImplTest() {
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
     * Test of findById method, of class TopicDaoImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        String id = "ff808081204cda9701204cdac24a00e6";
        TopicDao instance = (TopicDao) DIManager.getBean("TopicDao");
        Topic result = instance.findById(id);

        assertNotNull(result);
        System.out.println(result);
    }

    /**
     * Test of findByUserLoginId method, of class TopicDaoImpl.
     */
    @Test
    public void testFindByUserLoginId() {
        System.out.println("findByUserLoginId");
        String loginId = "admin";
        int maxNum = 10;
        TopicDao instance = (TopicDao) DIManager.getBean("TopicDao");
        List<Topic> result = instance.findByUserLoginId(loginId, maxNum);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of findByTopicLevel method, of class TopicDaoImpl.
     */
    @Test
    public void testFindByTopicLevel() {
        System.out.println("findByTopicLevel");
        int topiclevel = 1;
        int maxNum = 10;
        TopicDao instance = (TopicDao) DIManager.getBean("TopicDao");
        List<Topic> result = instance.findByTopicLevel(topiclevel, maxNum);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of findLT method, of class TopicDaoImpl.
     */
    @Test
    public void testFindLT() {
        System.out.println("findLT");
        //String loginId = "atea";
        String loginId = "wangqiang";
        int maxNum = 2;
        TopicDao instance = (TopicDao) DIManager.getBean("TopicDao");
        //List<Topic> result = instance.findLT(loginId, maxNum);
        List<Topic> result = instance.findLT(maxNum);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    @Test
    public void testFindLU() {
        System.out.println("findLU");

        int maxNum = 10;
        TopicDao instance = (TopicDao) DIManager.getBean("TopicDao");
        List<Topic> result = instance.findLU(maxNum);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }
}