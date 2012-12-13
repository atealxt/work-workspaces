package pdms.components.dao;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.pojo.Reply;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class ReplyDaoImplTest {

    public ReplyDaoImplTest() {
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
     * Test of findByUserLoginId method, of class ReplyDaoImpl.
     */
    @Test
    public void testFindByUserLoginId() {
        System.out.println("findByUserLoginId");
        String loginId = "atea";
        int maxNum = 1;
        ReplyDao instance = (ReplyDao) DIManager.getBean("ReplyDao");
        List<Reply> result = instance.findByUserLoginId(loginId, maxNum);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of findByTopicId method, of class ReplyDaoImpl.
     */
    @Test
    public void testFindByTopicId() {
        System.out.println("findByTopicId");
        String topicId = "ff808081204cda9701204cdac24a00e5";
        int maxNum = 10;
        ReplyDao instance = (ReplyDao) DIManager.getBean("ReplyDao");
        List<Reply> result = instance.findByTopicId(topicId, maxNum);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }
}