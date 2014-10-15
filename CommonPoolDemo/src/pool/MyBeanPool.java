package pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class MyBeanPool extends GenericObjectPool {

    public MyBeanPool() {
        super();
    }

    public MyBeanPool(final PoolableObjectFactory factory, final Config config) {
        super(factory, config);
    }

    public MyBeanPool(
            final PoolableObjectFactory factory,
            final int maxActive,
            final byte whenExhaustedAction,
            final long maxWait,
            final boolean testOnBorrow,
            final boolean testOnReturn) {
        super(factory, maxActive, whenExhaustedAction, maxWait, testOnBorrow, testOnReturn);
    }

    public MyBeanPool(
            final PoolableObjectFactory factory,
            final int maxActive,
            final byte whenExhaustedAction,
            final long maxWait,
            final int maxIdle,
            final boolean testOnBorrow,
            final boolean testOnReturn,
            final long timeBetweenEvictionRunsMillis,
            final int numTestsPerEvictionRun,
            final long minEvictableIdleTimeMillis,
            final boolean testWhileIdle) {
        super(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, testOnBorrow, testOnReturn,
              timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
    }

    public MyBeanPool(
            final PoolableObjectFactory factory,
            final int maxActive,
            final byte whenExhaustedAction,
            final long maxWait,
            final int maxIdle,
            final boolean testOnBorrow,
            final boolean testOnReturn) {
        super(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, testOnBorrow, testOnReturn);
    }

    public MyBeanPool(
            final PoolableObjectFactory factory,
            final int maxActive,
            final byte whenExhaustedAction,
            final long maxWait,
            final int maxIdle,
            final int minIdle,
            final boolean testOnBorrow,
            final boolean testOnReturn,
            final long timeBetweenEvictionRunsMillis,
            final int numTestsPerEvictionRun,
            final long minEvictableIdleTimeMillis,
            final boolean testWhileIdle,
            final long softMinEvictableIdleTimeMillis,
            final boolean lifo) {
        super(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn,
              timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle,
              softMinEvictableIdleTimeMillis, lifo);
    }

    public MyBeanPool(
            final PoolableObjectFactory factory,
            final int maxActive,
            final byte whenExhaustedAction,
            final long maxWait,
            final int maxIdle,
            final int minIdle,
            final boolean testOnBorrow,
            final boolean testOnReturn,
            final long timeBetweenEvictionRunsMillis,
            final int numTestsPerEvictionRun,
            final long minEvictableIdleTimeMillis,
            final boolean testWhileIdle,
            final long softMinEvictableIdleTimeMillis) {
        super(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn,
              timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle,
              softMinEvictableIdleTimeMillis);
    }

    public MyBeanPool(
            final PoolableObjectFactory factory,
            final int maxActive,
            final byte whenExhaustedAction,
            final long maxWait,
            final int maxIdle,
            final int minIdle,
            final boolean testOnBorrow,
            final boolean testOnReturn,
            final long timeBetweenEvictionRunsMillis,
            final int numTestsPerEvictionRun,
            final long minEvictableIdleTimeMillis,
            final boolean testWhileIdle) {
        super(factory, maxActive, whenExhaustedAction, maxWait, maxIdle, minIdle, testOnBorrow, testOnReturn,
              timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis, testWhileIdle);
    }

    public MyBeanPool(final PoolableObjectFactory factory, final int maxActive, final byte whenExhaustedAction, final long maxWait, final int maxIdle) {
        super(factory, maxActive, whenExhaustedAction, maxWait, maxIdle);
    }

    public MyBeanPool(final PoolableObjectFactory factory, final int maxActive, final byte whenExhaustedAction, final long maxWait) {
        super(factory, maxActive, whenExhaustedAction, maxWait);
    }

    public MyBeanPool(final PoolableObjectFactory factory, final int maxActive) {
        super(factory, maxActive);
    }

    public MyBeanPool(final PoolableObjectFactory factory) {
        super(factory);
    }
}
