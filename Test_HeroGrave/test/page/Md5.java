/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package page;

import com.herograve.util.ContentUtil;
import org.apache.struts2.views.util.ContextUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class Md5 {

    public Md5() {
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
        
        System.out.println(ContentUtil.EncoderByMd5("admin123"));
        
    }

}