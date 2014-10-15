package easycache;

import org.junit.Assert;
import org.junit.Test;

import easycache.wrapper.CacheWrapper;
import easycache.wrapper.LocalCacheWrapper;

public class EhCacheTest {

    @Test
    public void testCacheOverTimeEhCache() throws InterruptedException {
        try {
            final CacheWrapper businessCache = new LocalCacheWrapper("easycache.sample.BusinessBean");
            Assert.assertTrue(businessCache.put("MyBean", new MyBean()));
            Assert.assertNotNull(businessCache.get("MyBean"));
            Thread.sleep(5 * 1000);
            Assert.assertNull(businessCache.get("MyBean"));
        } catch (final CacheException e) {
        	Assert.fail(e.getMessage());
        }

        try {
            final CacheWrapper viewCache = new LocalCacheWrapper("easycache.sample.VeiwBean");
            Assert.assertTrue(viewCache.put("MyBean", new MyBean()));
            Assert.assertNotNull(viewCache.get("MyBean"));
            Thread.sleep(1 * 1000);
            Assert.assertNotNull(viewCache.get("MyBean"));
        } catch (final CacheException e) {
        	Assert.fail(e.getMessage());
        }
    }
}
