package test.mock;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import test.MyBean;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MyBean.class)
@PowerMockIgnore("org.apache.log4j.*")
public class PrivateMethodMockTest {

    private static final Logger logger = Logger.getLogger(PrivateMethodMockTest.class);

    @Test
    public void executePrivate() throws Exception {
        final MyBean bean = PowerMock.createPartialMock(MyBean.class, "testPrivate");
        PowerMock.expectPrivate(bean, "testPrivate", "a", 1).andReturn(123);
        PowerMock.replay(bean);

        final Method testPrivate = MyBean.class.getDeclaredMethod("testPrivate", String.class, int.class);
        testPrivate.setAccessible(true);
        final Object ret = testPrivate.invoke(bean, new Object[] { "a", 1 });
        logger.debug("ret: " + ret);
        Assert.assertEquals(123, ret);
    }
}

// 注意：mock的方法，不是想当然的执行完了再返回mock值，而是内容根本就不会执行，直接返回mock值。