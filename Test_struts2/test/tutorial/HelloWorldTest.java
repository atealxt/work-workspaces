/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tutorial;

import com.opensymphony.xwork2.ActionSupport;
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
public class HelloWorldTest {

    public HelloWorldTest() {
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
    @Test
    public void testExecute() {
        
        HelloWorld hello = new HelloWorld();
        hello.setName("World");
        String result = hello.execute();
        
        assertTrue("Expected a success result!", ActionSupport.SUCCESS.equals(result));
        
        final String msg = "Hello, World!";
        assertTrue("Expected the default message!", msg.equals(hello.getName()));
    }

    
    
    

}