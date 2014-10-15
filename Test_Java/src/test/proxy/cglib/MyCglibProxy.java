package test.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyCglibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    @SuppressWarnings("unchecked")
    public <T> T getProxyBean(final Class<?> clz) {

        enhancer.setSuperclass(clz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(
            final Object paramObject,
            final Method paramMethod,
            final Object[] paramArrayOfObject,
            final MethodProxy paramMethodProxy) throws Throwable {

        System.out.println("before");

        Object result = paramMethodProxy.invokeSuper(paramObject, paramArrayOfObject);

        System.out.println("after");

        return result;
    }

}