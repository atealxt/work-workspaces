package pool;

import org.apache.commons.pool.PoolableObjectFactory;

import entity.MyBean;

public class MyBeanFactory implements PoolableObjectFactory {

    /** makeObject is called whenever a new instance is needed */
    @Override
    public Object makeObject() throws Exception {
        MyBean o = new MyBean();
        System.out.println("makeObject: " + o);
        return o;
    }

    /** activateObject is invoked on every instance that has been passivated before it is borrowed from the pool. */
    @Override
    public void activateObject(final Object obj) throws Exception {
        System.out.println("activateObject: " + obj);
    }

    /**
     * destroyObject is invoked on every instance when it is being "dropped" from the pool (whether due to the response
     * from validateObject, or for reasons specific to the pool implementation.) There is no guarantee that the instance
     * being destroyed will be considered active, passive or in a generally consistent state.
     */
    @Override
    public void destroyObject(final Object obj) throws Exception {
        System.out.println("destroyObject: " + obj);
    }

    /** passivateObject is invoked on every instance when it is returned to the pool. */
    @Override
    public void passivateObject(final Object obj) throws Exception {
        System.out.println("passivateObject: " + obj);
    }

    /**
     * validateObject is invoked on activated instances to make sure they can be borrowed from the pool. validateObject
     * may also be used to test an instance being returned to the pool before it is passivated. It will only be invoked
     * on an activated instance.
     */
    @Override
    public boolean validateObject(final Object obj) {
        System.out.println("validateObject: " + obj);
        return true;
    }

}
