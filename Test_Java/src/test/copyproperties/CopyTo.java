/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package test.copyproperties;

import test.MyBean;

/**
 *
 * @author Administrator
 */
public class CopyTo {

    private int intCopy;
    private Integer integerCopy;
    private String stringCopy;

    private MyBean myBean = new MyBean();

    public CopyTo() {}

    public int getIntCopy() {
        return intCopy;
    }

    public void setIntCopy(final int intCopy) {
        this.intCopy = intCopy;
    }

    public Integer getIntegerCopy() {
        return integerCopy;
    }

    public void setIntegerCopy(final Integer integerCopy) {
        this.integerCopy = integerCopy;
    }

    public String getStringCopy() {
        return stringCopy;
    }

    public void setStringCopy(final String stringCopy) {
        this.stringCopy = stringCopy;
    }

    public MyBean getMyBean() {
        return myBean;
    }

    public void setMyBean(final MyBean myBean) {
        this.myBean = myBean;
    }
}
