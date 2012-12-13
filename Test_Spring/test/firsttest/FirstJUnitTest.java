/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firsttest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class FirstJUnitTest {

    public FirstJUnitTest() {
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

        //BeanFactory factory = new XmlBeanFactory(new FileSystemResource("helloworld.xml"));
        BeanFactory factory = new ClassPathXmlApplicationContext("helloworld.xml");
        Firsttest ft = (Firsttest) factory.getBean("firsttest");
        assertEquals(ft.getHello().getMsg(), "Hello World!");
        assertEquals(ft.getName(), "aあ啊");
        ft.aopTest();
        ft.aopTest(ft);
    }
}