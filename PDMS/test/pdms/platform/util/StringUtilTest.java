package pdms.platform.util;

import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pdms.components.pojo.User;
import pdms.platform.service.impl.A0900FileServiceImpl;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class StringUtilTest {

    public StringUtilTest() {
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
     * Test of getMD5Code method, of class StringUtil.
     */
    @Test
    public void testGetMD5Code() throws Exception {
        System.out.println("getMD5Code");
        System.out.println(StringUtil.getMD5Code("aaa"));
    }

    /**
     * Test of getFirstStr method, of class StringUtil.
     */
    @Test
    public void testGetFirstStr() throws Exception {
        System.out.println("getFirstStr");
        String src = "aaあ啊aa";
        int len = 4;
        String result = StringUtil.getFirstStr(src, len);
        System.out.println(result);        
    }

    /**
     * Test of getDateFormat method, of class StringUtil.
     */
    @Test
    public void testGetDateFormat() throws Exception {
        System.out.println("getDateFormat");
        Date date = new Date(new java.util.Date().getTime());
        String result = StringUtil.getDateFormat(date);
        System.out.println(result);
    }

    /**
     * Test of delFtmlTag method, of class StringUtil.
     */
    @Test
    public void testDelFtmlTag() {
        System.out.println("delFtmlTag");
        String src = "aa<a>bb</a>cc&lt;dd&nbsp;ee&;ff&abc123;gg";
        String expResult = "aabbccddee&;ff&abc123;gg";
        String result = StringUtil.delFtmlTag(src);
        System.out.println(result);
        assertEquals(expResult, result);
        
    }
}