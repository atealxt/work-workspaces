package test.security;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;

public class TestBase {

    protected Logger logger = Logger.getLogger(getClass());

    protected String str = "测试test";
    protected String url = "http://atealxt.appspot.com/guestbook?p=";
    protected String p = "a=1&b=2&c=3&d=你好&最后一个参数=haha";

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}
}
