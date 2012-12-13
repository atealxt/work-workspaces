package test.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import org.junit.Test;

import test.MyBean;

/**
 * 一般用于标准javabean，其实不是反射<br>
 * 详细内容参见org.apache.jasper.runtime。introspecthelper
 **/
public class BeanInfoTest {

    @Test
    public void excute() throws IntrospectionException {

        BeanInfo info = Introspector.getBeanInfo(MyBean.class);
        if (info == null) {
            return;
        }

        MethodDescriptor[] methodDescriptor = info.getMethodDescriptors();
        if (methodDescriptor == null) {
            return;
        }
        for (MethodDescriptor descriptor : methodDescriptor) {
            System.out.println(descriptor.getMethod());
        }

        System.out.println();
        PropertyDescriptor[] propertyDescriptor = info.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : propertyDescriptor) {
            System.out.println(descriptor.getDisplayName());
            System.out.println(descriptor.getWriteMethod());
        }
    }
}
