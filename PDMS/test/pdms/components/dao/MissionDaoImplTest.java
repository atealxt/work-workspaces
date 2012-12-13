package pdms.components.dao;

import pdms.components.dao.impl.MissionDaoImpl;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.pojo.Mission;
import pdms.platform.configeration.DIManager;
import pdms.platform.core.PdmsException;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class MissionDaoImplTest {

    public MissionDaoImplTest() {
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
     * Test of findByUserLoginId method, of class MissionDaoImpl.
     */
    @Test
    public void testFindByUserLoginId() {
        System.out.println("findByUserLoginId");
        String loginId = "wangqiang";
        MissionDao instance = (MissionDao) DIManager.getBean("MissionDao");
        List<Mission> result = instance.findByUserLoginId(loginId);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of CountByTime4Chart method, of class MissionDaoImpl.
     */
    @Test
    public void testCountByTime4Chart() {
        System.out.println("CountByTime4Chart");
        Calendar cal = Calendar.getInstance();
        cal.set(2008, 0, 1, 0, 0, 0);
        Date start = cal.getTime();
        cal.set(2010, 0, 1, 0, 0, 0);
        Date end = cal.getTime();

        MissionDaoImpl instance = new MissionDaoImpl();
        int result = instance.CountByTime4Chart(start, end);
        assertTrue(result > 0);
        System.out.println(result);
    }

    /**
     * Test of CountAllByTime4Chart method, of class MissionDaoImpl.
     */
    @Test
    public void testCountAllByTime4Chart() {
        System.out.println("CountAllByTime4Chart");
        Calendar cal = Calendar.getInstance();
        cal.set(2008, 0, 1, 0, 0, 0);
        Date start = cal.getTime();
        cal.set(2010, 0, 1, 0, 0, 0);
        Date end = cal.getTime();
        MissionDaoImpl instance = new MissionDaoImpl();
        int result = instance.CountAllByTime4Chart(start, end);
        assertTrue(result > 0);
        System.out.println(result);
    }

    /**
     * Test of findBySubmitFileId method, of class MissionDaoImpl.
     */
    @Test
    public void testFindBySubmitFileId() throws PdmsException {
        System.out.println("findBySubmitFileId");
        MissionDao instance = (MissionDao) DIManager.getBean("MissionDao");
        Mission result = instance.findBySubmitFileId("2c90e2412114ad87012114b052780001");
        assertNotNull(result);
        System.out.println(result.getContent());
    }
}