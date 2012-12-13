package test.webservice.jws.timeservice;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import test.MyBean;

@XmlType
public class MyBeanList {

    private List<MyBean> beans;

    @XmlElement
    public List<MyBean> getBeans() {
        return beans;
    }

    public void setBeans(final List<MyBean> beans) {
        this.beans = beans;
    }
}
