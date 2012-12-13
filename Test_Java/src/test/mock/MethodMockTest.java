package test.mock;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

import test.MyBean;

public class MethodMockTest {

    @Test
    public void execute1() {
        // 模拟getDdd，不设返回值
        final MyBean myBean = EasyMock.createMockBuilder(MyBean.class).addMockedMethod("getDdd").createMock();
        myBean.setDdd("ddd");
        Assert.assertNull(myBean.getDdd());
    }

    @Test
    public void execute2() {
        final MyBean myBean = EasyMock.createMockBuilder(MyBean.class).addMockedMethod("getDdd").createMock();
        EasyMock.expect(myBean.getDdd()).andReturn("hello mock");
        EasyMock.replay(myBean);

        myBean.setDdd("ddd");
        Assert.assertEquals("hello mock", myBean.getDdd());
    }
}
