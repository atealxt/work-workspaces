package test.proxy.cglib;

import org.junit.Test;

import test.MyBean;

public class MyCglibProxyTest {

    @Test
    public void execute() {

        MyBean myBean = new MyCglibProxy().getProxyBean(MyBean.class);
        myBean.a(1);
    }
}
