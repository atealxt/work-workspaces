/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proxy.java;

import java.lang.reflect.Proxy;

/**
 * A Simple AOP Container
 *
 */
public class AopContainer {

    /**
     * @param clazz 被代理的接口
     * @param obj 被代理的类实例
     */
    public static <T> T getBean(final Class<T> clazz, final T obj) {
        assert clazz.isInterface();

        final T bean = clazz.cast(Proxy.newProxyInstance(clazz.getClassLoader(),
            new Class[]{clazz}, new AopInvocationHandlerImpl(obj)));
        return bean;
    }

    /**
     * @param clazz 被代理的接口
     * @param obj 被代理的类实例
     * @param name 后通知类Inform的方法的参数
     */
    public static <T> T getBean(final Class<T> clazz, final T obj, final String name) {
        assert clazz.isInterface();

        final T bean = clazz.cast(Proxy.newProxyInstance(clazz.getClassLoader(),
            new Class[]{clazz}, new AopInvocationHandlerImpl(obj, new Inform(name))));
        return bean;
    }

    /**
     * @param obj 被代理的类实例
     */
    public static Object getBean(final Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
            obj.getClass().getInterfaces(), new AopInvocationHandlerImpl(obj));
    }

    /**
     * @param obj 被代理的类实例
     * @param name 后通知类Inform的方法的参数
     */
    public static Object getBean(final Object obj, final String name) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
            obj.getClass().getInterfaces(), new AopInvocationHandlerImpl(obj, new Inform(name)));
    }
}