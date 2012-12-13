/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proxyfactorybean;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class PersonImplTest {

    private BeanFactory factory;

    public PersonImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        factory = new ClassPathXmlApplicationContext("proxyfactorybean.xml");
    }

    @After
    public void tearDown() {
    }

    //@Test
    public void hello() {
        Person person = (Person) factory.getBean("person");
        System.out.println(person);
    }

    @Test
    public void proxyTemplate() {
        Person person = (Person) factory.getBean("anotherperson");
        System.out.println(person);
    }
}