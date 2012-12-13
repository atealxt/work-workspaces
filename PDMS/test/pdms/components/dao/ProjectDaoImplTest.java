package pdms.components.dao;

import pdms.components.dao.impl.ProjectDaoImpl;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.pojo.Project;
import pdms.platform.configeration.DIManager;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class ProjectDaoImplTest {

    public ProjectDaoImplTest() {
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
     * Test of findById method, of class ProjectDaoImpl.
     */
    @Test
    public void testFindById() {
        System.out.println("findById");
        int id = 2;
        ProjectDao dao = (ProjectDao) DIManager.getBean("ProjectDao");
        Project result = dao.findById(id);

        assertNotNull(result);
        System.out.println(result);
    }

    /**
     * Test of findByName method, of class ProjectDaoImpl.
     */
    @Test
    public void testFindByName() throws Exception {
        System.out.println("findByName");
        String name = "图书管理系统";
        ProjectDao dao = (ProjectDao) DIManager.getBean("ProjectDao");
        Project result = dao.findByName(name);

        assertNotNull(result);
        System.out.println(result);
    }

    /**
     * Test of findByUserLoginId method, of class ProjectDaoImpl.
     */
    @Test
    public void testFindByUserLoginId() {
        System.out.println("findByName");
        String loginId = "atea";
        ProjectDao dao = (ProjectDao) DIManager.getBean("ProjectDao");
        List<Project> result = dao.findByUserLoginId(loginId);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of findByManagerLoginId method, of class ProjectDaoImpl.
     */
    @Test
    public void testFindByManagerLoginId() {
        System.out.println("findByManagerLoginId");
        String loginId = "atea";
        ProjectDao dao = (ProjectDao) DIManager.getBean("ProjectDao");
        List<Project> result = dao.findByManagerLoginId(loginId);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of Insert method, of class ProjectDaoImpl.
     */
    //@Test
    public void testInsert() {
        System.out.println("Insert");
        Project obj = null;
        ProjectDaoImpl instance = new ProjectDaoImpl();
        int expResult = 0;
        int result = instance.Insert(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Update method, of class ProjectDaoImpl.
     */
    //@Test
    public void testUpdate() {
        System.out.println("Update");
        Project obj = null;
        ProjectDaoImpl instance = new ProjectDaoImpl();
        int expResult = 0;
        int result = instance.Update(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findProjects method, of class ProjectDaoImpl.
     */
    @Test
    public void testFindProjects() {
        System.out.println("findProjects");
        ProjectDao dao = (ProjectDao) DIManager.getBean("ProjectDao");
        List<Project> result = dao.findProjects();
        System.out.println(result);
    }
}