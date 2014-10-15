package easycache.util;

import junit.framework.Assert;

import org.junit.Test;

import easycache.MyBean;
import easycache.utils.CacheKeyUtils;

public class CacheKeyUtilsTest {

    @Test
    public void testGenCacheKey() {
        final MyBean o = new MyBean();
        o.setA(123);
        o.setB("hello cache");
        o.setC(new Float[] { 123.4F, 567.8F });
        o.setD(new double[][] { { 1.2, 3.4 }, { 5.6, 7.8 } });

        final String key = CacheKeyUtils.genCacheKey(o);
        Assert.assertEquals(key, CacheKeyUtils.genCacheKey(o));

        o.setB("abcde");
        Assert.assertNotSame(key, CacheKeyUtils.genCacheKey(o));
    }
}
