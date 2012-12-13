package test.reflection;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import test.MyBean;

public class ReflectionTest {

    private MyBean bean = new MyBean();

    @Test
    public void excuteJava() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {

        System.out.println(MyBeanMethods.getMethodA().invoke(bean, 1));
        MyBeanMethods.getMethodB().invoke(bean, 2);

    }

    @Test
    public void excuteCglib() throws InvocationTargetException {

        System.out.println(MyBeanMethods.getFastMethodA().invoke(bean, new Object[] { 1 }));
//        MyBeanMethods.getFastMethodB().invoke(bean, new Object[] { 2 });
    }
}
