package pdms.components.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.components.pojo.Example;
import pdms.platform.configeration.DIManager;
import pdms.platform.core.EasyDao;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class DaoTest {

    public DaoTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        EasyDao dao = (EasyDao) DIManager.getBean("EasyDao");
        System.out.println(dao.Select("from Example"));

        Example example = new Example();
        example.setName("aaa");
        System.out.println(dao.Insert(example));
        System.out.println(dao.Select("from Example"));

        example.setName("aaab");
        System.out.println(dao.Update(example));
        System.out.println(dao.Select("from Example"));

        System.out.println(dao.Delete(example));
        System.out.println(dao.Select("from Example"));
    }

//    @Test
//    public void testExecute() {
//        EasyDao dao = (EasyDao) DIManager.getBean("EasyDao");
//        System.out.println(dao.Execute("insert into Example values('aaa','hahahahaha')"));
//    }
}