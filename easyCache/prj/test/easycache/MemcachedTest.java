package easycache;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import easycache.wrapper.DistributedCachedWrapper;

public class MemcachedTest {

    private DistributedCachedWrapper cache;

    @Before
    public void setUp() throws CacheException {
        cache = new DistributedCachedWrapper();
    }

    @After
    public void tearDown() throws CacheException {
        System.out.println(cache.statistics());
    }

    @Test
    public void testCache1() throws InterruptedException {
        final String key = "ad607d41f7d029cffed6b9c31af15582";
        Assert.assertTrue(cache.put(key, "wwaa"));
        System.out.println(cache.get(key));
    }

    @Test
    public void testCacheOverTimeMemcached() throws InterruptedException {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 3);
        Assert.assertTrue(cache.put("MyBean", new MyBean(), cal.getTime()));
        Assert.assertNotNull(cache.get("MyBean"));
        Thread.sleep(1 * 1000);
        Assert.assertNotNull(cache.get("MyBean"));
        Thread.sleep(5 * 1000);
        Assert.assertNull(cache.get("MyBean"));

        cache.remove("MyBean2");
        Assert.assertTrue(cache.put("MyBean2", new MyBean(), new Date()));
        Thread.sleep(2 * 1000);
        Assert.assertNull(cache.get("MyBean2"));

        Assert.assertTrue(cache.put("MyBean3", new MyBean(), 3));
        Assert.assertNotNull(cache.get("MyBean3"));
        Thread.sleep(2 * 1000);
        Assert.assertNotNull(cache.get("MyBean3"));
        Thread.sleep(1 * 1000);
        Assert.assertNull(cache.get("MyBean3"));
    }

    @Test
    public void testGetMultiValue() {
        final String[] keys = { "a", "b" };
        final String[] values = { "v1", "v2" };
        cache.put(keys[0], values[0]);
        cache.put(keys[1], values[1]);
        final Map<String, Object> map = cache.get(keys);
        Assert.assertTrue(map.containsKey(keys[0]));
        Assert.assertEquals(values[0], map.get(keys[0]));
        Assert.assertTrue(map.containsKey(keys[1]));
        Assert.assertEquals(values[1], map.get(keys[1]));
    }

    // @Test
    public void testAddAndDelServer() {
        cache.put("s1", 1);
        cache.put("s2", 2);
        Assert.assertNotNull(cache.get("s1"));
        Assert.assertNotNull(cache.get("s2"));

        cache.addServer("172.16.16.224:13000", 5);

        for (int i = 3; i <= 10; i++) {
            Assert.assertTrue(cache.put("s" + i, i));
        }

        cache.removeServer("127.0.0.1:11211");

        Assert.assertNull(cache.get("s1"));
        Assert.assertNull(cache.get("s2"));

        for (int i = 3; i <= 10; i++) {
            System.out.println("s" + i + ": " + cache.get("s" + i));
        }
    }

    /**
     * 节点损坏了的话?<br>
     * 本地起一个服务，加上服务器的；<br>
     * 加几条数据；<br>
     * 停掉本地的；<br>
     * get看看情况<br>
     * By default the java client will failover to a new server when a server dies. It will also failback to the
     * original if it detects that the server comes back (it checks the server in a falling off pattern).
     *
     * If you want to disable this (useful if you have flapping servers), there are two settings to handle this.
     *
     * pool.setFailover( false ); pool.setFailback( false );
     */
    // @Test
    public void testServerDown() {
        cache.addServer("172.16.16.224:13000", 5);

        for (int i = 1; i <= 10; i++) {
            Assert.assertTrue(cache.put("testServerDown" + i, i));
        }

        try {
            // 这10秒钟去停一台服务，模拟服务器宕机
            System.out.println("start sleep");
            Thread.sleep(10 * 1000);
            System.out.println("end sleep");
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 10; i++) {
            System.out.println("testServerDown" + i + ": " + cache.get("testServerDown" + i));
        }

        for (int i = 11; i <= 20; i++) {
            Assert.assertTrue(cache.put("testServerDown" + i, i));
        }
    }
}
