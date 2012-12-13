package test.reflection;

import java.lang.reflect.Method;

import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import test.MyBean;

/** 缓存Method对象可以提高性能（这里用简单的静态变量和方法来举例） */
public final class MyBeanMethods {

    private static Method methodA;
    private static Method methodB;
    private static FastClass cglibBeanClass = FastClass.create(MyBean.class);
    private static FastMethod fastMethodA;
    private static FastMethod fastMethodB;

    private MyBeanMethods() {}

    static {
        try {
            methodA = MyBean.class.getDeclaredMethod("a", Integer.class);
            methodB = MyBean.class.getDeclaredMethod("b", int.class);
            methodB.setAccessible(true);
            fastMethodA = cglibBeanClass.getMethod(methodA);
//            fastMethodB = cglibBeanClass.getMethod(methodB);//cglib不支持调用私有方法
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static Method getMethodA() {
        return methodA;
    }

    public static Method getMethodB() {
        return methodB;
    }

    public static FastMethod getFastMethodA() {
        return fastMethodA;
    }

    public static FastMethod getFastMethodB() {
        return fastMethodB;
    }

    public static void main(final String args[]){

    }
}
