/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package test.copyproperties;

import java.lang.reflect.InvocationTargetException;

import junit.framework.Assert;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.Test;

import test.MyBean;

/**
 *
 * @author Administrator
 */
public class Test_getProperties {

    @Test
    public void getProperty() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String str = "test!";
        MyBean bean = new MyBean();
        bean.setDdd(str);
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        Assert.assertEquals(str, propertyUtilsBean.getSimpleProperty(bean, "ddd"));

        CopyTo bean2 = new CopyTo();
        bean2.setMyBean(bean);
        Assert.assertEquals(str, propertyUtilsBean.getNestedProperty(bean2, "myBean.ddd"));
    }
}
