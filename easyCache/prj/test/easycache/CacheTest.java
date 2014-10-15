package easycache;

import org.junit.Assert;
import org.junit.Test;

import easycache.wrapper.CacheWrapper;

public class CacheTest {

    @Test
    public void testCache() {
        try {
            CacheWrapper cache = new CacheWrapper();
            Assert.assertTrue(cache.put("MyBean", new MyBean()));
            Assert.assertNotNull(cache.get("MyBean"));
            Assert.assertNotNull(cache.get("MyBean", MyBean.class));
            Assert.assertTrue(cache.remove("MyBean"));
            Assert.assertNull(cache.get("MyBean"));
        } catch (CacheException e) {
            e.printStackTrace();
        }
    }
}
