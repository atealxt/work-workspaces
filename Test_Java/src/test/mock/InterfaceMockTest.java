package test.mock;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InterfaceMockTest {

    private HttpServletRequest reqMock;

    @Before
    public void setUp() {
        // 手动创建MocksControl，可以支持跨mock对象检测方法调用顺序
        IMocksControl ctrl = EasyMock.createStrictControl();
        reqMock = ctrl.createMock(HttpServletRequest.class);

        EasyMock.expect(reqMock.getParameter("key")).andReturn("value1");
        EasyMock.expect(reqMock.getAttribute("key")).andReturn("value2");
        EasyMock.replay(reqMock);
    }

    @Test
    public void execute() {
        Assert.assertEquals("value1", reqMock.getParameter("key"));
        Assert.assertEquals("value2", reqMock.getAttribute("key"));

        // verify用来验证mock对象的方法是不是按顺序、指定次数的执行。
        // 比如多写一个EasyMock.expec，但没调用的话，就通不过验证了。
        EasyMock.verify(reqMock);
    }
}
