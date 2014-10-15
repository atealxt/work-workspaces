/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proxy.java;

/**
 * Test AOP
 *
 */
public class TestAop {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        //test1
        //先创建被代理类，才能被AOP
        DoBusiness business = AopContainer.getBean(DoBusiness.class, new DoBusinessImpl());
        business.printNothing();
        business.throwException();
        //上面的new DoBusinessImpl()可以通过IOC灵活掌握

        //test2
        //原先的类已被AOP，所以再创建时需要创建新的。否则AOP会叠加:
        //business = AopContainer.getBean(DoBusiness.class, business, "Tom");
        business = AopContainer.getBean(DoBusiness.class, new DoBusinessImpl(), "Tom");
        business.printNothing();
        business.throwException();
    }
}