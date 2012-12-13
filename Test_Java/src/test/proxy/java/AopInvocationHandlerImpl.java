/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.proxy.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopInvocationHandlerImpl implements InvocationHandler {

    private Object bizPojo;
    private Inform inf;

    public AopInvocationHandlerImpl(final Object bizPojo) {
        this.bizPojo = bizPojo;
    }

    public AopInvocationHandlerImpl(final Object bizPojo, final Inform inf) {
        this.bizPojo = bizPojo;
        this.inf = inf;
    }

    /*
     * (non - Javadoc)
     *
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
     *      java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke(final Object proxy, final Method method, final Object[] args)
        throws Throwable {
        if ("printNothing".equals(method.getName())) {
            return method.invoke(bizPojo, args);
        }

        Object o = null;
        System.out.println("Start:" + method.getName());
        try {
            o = method.invoke(bizPojo, args);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Exception Occured!");
        } finally {
            System.out.println("End:" + method.getName());
            if (inf != null) {
                inf.say();
            }
        }
        return o;
    }
}