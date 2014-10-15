package test.xml;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import test.MyBean;

import com.thoughtworks.xstream.XStream;

public class XStreamDemo {

    private XStream xstream;

    @Before
    public void setUp() {
        xstream = new XStream();
        xstream.alias("MyBeanXml", MyBean.class);
    }

    @Test
    public void Pojo2Xml() {
        MyBean myBean = new MyBean();
        myBean.setCcc(123);
        myBean.setDdd("a啊b");
        String xml = xstream.toXML(myBean);
        String xmlExpected = "" //
                + "<MyBeanXml>\n"//
                + "  <ccc>123</ccc>\n"//
                + "  <ddd>a啊b</ddd>\n"//
                + "</MyBeanXml>";
        Assert.assertEquals(xmlExpected, xml);
    }

    @Test
    public void Xml2Pojo() {
        String xml = "" //
                + "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"//
                + "<MyBeanXml>\n"//
                + "  <ccc>123</ccc>\n"//
                + "  <ddd>a啊b</ddd>\n"//
                + "</MyBeanXml>";
        MyBean myBean = (MyBean) xstream.fromXML(xml);
        Assert.assertEquals(123, myBean.getCcc());
        Assert.assertEquals("a啊b", myBean.getDdd());
    }
}
