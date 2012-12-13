package core.old;

import java.io.File;
import java.lang.reflect.Method;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.appengine.tools.development.ApiProxyLocalImpl;
import com.google.apphosting.api.ApiProxy;

import core.TestEnvironment;

public class TestUtil {

    private static AbstractApplicationContext ctx;

    public void setUp() throws Exception {
        ApiProxy.setEnvironmentForCurrentThread(new TestEnvironment());
        ApiProxy.setDelegate(new ApiProxyLocalImpl(new File(".")) {});
    }

    public void tearDown() throws Exception {
        ApiProxy.setDelegate(null);
        ApiProxy.setEnvironmentForCurrentThread(null);
    }

    @SuppressWarnings("unchecked")
    public static Method getMethod(String classPackage, String exeMethod, Class[] param) throws Exception {
        try {
            Class cls = Class.forName(classPackage);
            Method method = cls.getDeclaredMethod(exeMethod, param);
            method.setAccessible(true);
            return method;
        } catch (Exception e) {
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        if (ctx != null) {
            return (T) ctx.getBean(name);
        } else {
            ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            ctx.registerShutdownHook();
            return (T) ctx.getBean(name);
        }
    }
}
