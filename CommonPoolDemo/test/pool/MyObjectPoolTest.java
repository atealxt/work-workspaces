package pool;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import entity.MyBean;

public class MyObjectPoolTest {

    private MyBeanPool pool;

    @Before
    public void setUp() {
        pool = new MyBeanPool(new MyBeanFactory(), 1);
    }

    @Test
    public void execute() throws Exception {
        MyBean bean1 = (MyBean) pool.borrowObject();
        Assert.assertNotNull(bean1);
        pool.returnObject(bean1);//returnObject must use in try finally
        MyBean bean2 = (MyBean) pool.borrowObject();
        Assert.assertEquals(bean2, bean1);
    }
}
