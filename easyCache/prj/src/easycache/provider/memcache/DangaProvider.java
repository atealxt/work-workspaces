package easycache.provider.memcache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

import easycache.provider.CacheConfig;
import easycache.provider.CacheProvider;
import easycache.provider.ObjectLevelTimer;
import easycache.utils.PropertiesUtils;

public class DangaProvider implements CacheProvider, ObjectLevelTimer, CacheConfig {

    protected static MemCachedClient cache = new MemCachedClient();
    private final static Logger logger = Logger.getLogger(DangaProvider.class);

    static {
        try {
            init();
        } catch (final FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected static void init() throws FileNotFoundException, IOException {
        PropertiesUtils.load("memcached.properties");

        final SockIOPool pool = SockIOPool.getInstance();
        final String[] servers = PropertiesUtils.getValue("memcached.servers").split(",");
        pool.setServers(servers);

        final String[] strWeights = PropertiesUtils.getValue("memcached.weights").split(",");
        final Integer[] weights = new Integer[strWeights.length];
        for (int i = 0; i < strWeights.length; i++) {
            weights[i] = Integer.valueOf(strWeights[i]);
        }
        pool.setWeights(weights);

        // 这三个是配对用的。如果想自动监控服务器失败与恢复，需要把这三个属性同时设为true。至少failover和aliveCheck要同时设为true
        // 注意，设这个参数会影响到性能，毕竟每次都要check嘛。可以考虑用专门的监控软件（比如nagios）来代替它。
        pool.setFailover(Boolean.valueOf(PropertiesUtils.getValue("memcached.failover")));
        pool.setFailback(Boolean.valueOf(PropertiesUtils.getValue("memcached.failback")));
        pool.setAliveCheck(Boolean.valueOf(PropertiesUtils.getValue("memcached.aliveCheck")));

        pool.setInitConn(Integer.valueOf(PropertiesUtils.getValue("memcached.initialConnections")));
        pool.setMinConn(Integer.valueOf((PropertiesUtils.getValue("memcached.minSpareConnections"))));
        pool.setMaxConn(Integer.valueOf((PropertiesUtils.getValue("memcached.maxSpareConnections"))));
        pool.setMaxIdle(Long.valueOf(PropertiesUtils.getValue("memcached.maxIdleTime")));
        pool.setMaintSleep(Long.valueOf(PropertiesUtils.getValue("memcached.maintSleep")));
        pool.setNagle(Boolean.valueOf(PropertiesUtils.getValue("memcached.nagle")));
        pool.setSocketTO(Integer.valueOf(PropertiesUtils.getValue("memcached.socketTimeOut")));
        pool.setSocketConnectTO(Integer.valueOf(PropertiesUtils.getValue("memcached.socketConnectTimeOut")));

        pool.initialize();
    }

    @Override
    public void buildCache() {
    }

    // 高并发下get可能会get出脏数据来，这应该是可以接受的。
    // 如果对于数据严谨性要求高的话，之前做一下keyExists。

    @Override
    public Object get(final String key) {
        return cache.get(key);
    }

    @Override
    public Map<String, Object> get(final String keys[]) {
        return cache.getMulti(keys);
    }

    @Override
    public boolean put(final String key, final Object value) {
        // set和add的区别

        // set
        // Most common command. Store this data, possibly overwriting any existing data. New items are at the top of the
        // LRU.

        // add
        // Store this data, only if it does not already exist. New items are at the top of the LRU. If an item already
        // exists and an add fails, it promotes the item to the front of the LRU anyway.

        return cache.set(key, value);
    }

    @Override
    public boolean put(final String key, final Object value, final Date expiry) {
        return cache.set(key, value, expiry);
    }

    @Override
    public boolean put(final String key, final Object value, final int expirySec) {
        return cache.set(key, value, new Date(expirySec * 1000L));
    }

    @Override
    public boolean remove(final String key) {
        return cache.delete(key);
    }

    // 2.0版不支持动态add/remove Server(api有bug)，如果改为最新的2.5版就没问题了。

    @Override
    public void addServer(final String ip, final int weights) {
        final SockIOPool pool = SockIOPool.getInstance();
        final Set<String> servers = new HashSet<String>(Arrays.asList(pool.getServers()));
        if (servers.contains(ip)) {
            return;
        }
        final String[] arrServers = new String[servers.size() + 1];
        final Integer[] arrWeights = new Integer[arrServers.length];
        System.arraycopy(pool.getServers(), 0, arrServers, 0, arrServers.length - 1);
        System.arraycopy(pool.getWeights(), 0, arrWeights, 0, arrWeights.length - 1);
        arrServers[arrServers.length - 1] = ip;
        arrWeights[arrWeights.length - 1] = weights;
        pool.setServers(arrServers);
        pool.setWeights(arrWeights);

        pool.shutDown();
        pool.initialize();
    }

    @Override
    public void removeServer(final String ip) {
        final SockIOPool pool = SockIOPool.getInstance();
        final String[] srcServers = pool.getServers();

        int index = -1;
        for (int i = 0; i < srcServers.length; i++) {
            if (!srcServers[i].equals(ip)) {
                continue;
            }
            index = i;
        }
        if (index < 0) {
            return;
        }

        final Set<String> servers = new HashSet<String>(Arrays.asList(srcServers));
        servers.remove(ip);
        pool.setServers(servers.toArray(new String[] {}));

        final List<Integer> weights = new ArrayList<Integer>(Arrays.asList(pool.getWeights()));
        weights.remove(index);
        pool.setWeights(weights.toArray(new Integer[] {}));

        pool.shutDown();
        pool.initialize();
    }

    @Override
    public String statistics() {
        return cache.stats().toString();
    }

    /**
     * CAS操作<br>
     * Memcached是通过cas协议实现原子更新，所谓原子更新就是compare and
     * set，原理类似乐观锁，每次请求存储某个数据同时要附带一个cas值，memcached比对这个cas值与当前存储数据的cas值是否相等，如果相等就让新的数据覆盖老的数据，如果不相等就认为更新失败，这在并发环境下特别有用。
     */
}

// *分布式算法
// danga java客户端不支持Consistent Hashing，xmemcached支持