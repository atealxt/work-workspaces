package pdms.components.dao;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.pojo.User;
import pdms.components.vo.A0500UserMVo;
import pdms.platform.configeration.DIManager;
import pdms.platform.core.PdmsException;
import pdms.platform.service.A0500UserMService;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class UserDaoImplTest {

    public UserDaoImplTest() {
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
     * Test of findByLoginIdAndPsw method, of class UserDaoImpl.
     */
//    @Test
    public void testFindByLoginIdAndPsw() throws Exception {
        System.out.println("findByLoginIdAndPsw");
        UserDao dao = (UserDao) DIManager.getBean("UserDao");
        String loginId = "pdms";
        String psw = "pdms";
        User result = dao.findByLoginIdAndPsw(loginId, StringUtil.getMD5Code(psw));
        assertNotNull(result);
        System.out.println(result);

        loginId = "pdms";
        psw = "nopsw";
        result = dao.findByLoginIdAndPsw(loginId, psw);
        assertNull(result);
    }

    /**
     * Test of findByProjectId method, of class UserDaoImpl.
     */
//    @Test
    public void testFindByProjectId() {
        System.out.println("findByProjectId");
        int projectId = 2;
        UserDao dao = (UserDao) DIManager.getBean("UserDao");
        List<User> result = dao.findByProjectId(projectId);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of findManagerByProjectId method, of class UserDaoImpl.
     */
//    @Test
    public void testFindManagerByProjectId() {
        System.out.println("findManagerByProjectId");
        int projectId = 2;
        UserDao dao = (UserDao) DIManager.getBean("UserDao");
        List<User> result = dao.findByProjectId(projectId, true);

        assertTrue(result.size() > 0);
        System.out.println(result);
    }

    /**
     * Test of Update method, of class UserDaoImpl.
     */
//    @Test
    public void testUpdate() throws PdmsException {
        System.out.println("Update");
        UserDao dao = (UserDao) DIManager.getBean("UserDao");
        String loginId = "wangqiang";
        User result = dao.findByLoginId(loginId);
        assertNotNull(result);

        result.setIp("172.16.98.4");
        System.out.println(dao.Update(result));
    }

    /**
     * Test of findAll method, of class UserDaoImpl.
     */
//    @Test
    public void testFindAll() {
        System.out.println("findAll");
        int maxNum = -1;
        int startNum = 0;
        UserDao dao = (UserDao) DIManager.getBean("UserDao");
        List<User> result = dao.findAll(maxNum, startNum);
        assertTrue(result.size() > 0);
        System.out.println(result.size());
    }

    /**
     * Test of findAllManager method, of class UserDaoImpl.
     */
//    @Test
    public void testFindAllManager() {
        System.out.println("findAllManager");
        int maxNum = -1;
        int startNum = 0;
        UserDao dao = (UserDao) DIManager.getBean("UserDao");
        List<User> result = dao.findAllManager(maxNum, startNum);
        assertTrue(result.size() > 0);
        System.out.println(result.size());
    }

    /**
     * Test of findAllUsrs method, of class UserDaoImpl.
     */
//    @Test
    public void testFindAllUsrs() {
        System.out.println("findAllUsrs");
        int maxNum = -1;
        int startNum = 0;
        UserDao dao = (UserDao) DIManager.getBean("UserDao");
        List<User> result = dao.findAllUsrs(maxNum, startNum);
        assertTrue(result.size() > 0);
        System.out.println(result.size());
    }

    @Test
    public void testManyToMany() {
//        UserDao dao = (UserDao) DIManager.getBean("UserDao");
//        GroupDao gdao = (GroupDao) DIManager.getBean("GroupDao");
//
//        User user = new User();
//        user.setLoginid("aaa");
//        Group group = gdao.findById(1);
//        Set set = new HashSet(0);
//        set.add(group);
//        user.setGroups(set);
//        dao.Insert(user);
//
//        user = dao.findByLoginId("aaa");
//        System.out.println(user.getGroups());
//
//        user.setGroups(new HashSet(0));
//        dao.Update(user);
//
//        user = dao.findByLoginId("aaa");
//        System.out.println(user.getGroups());
//
//        dao.Delete(user);

    }
}








