package easycache.provider.memcache;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import easycache.provider.CacheConfig;
import easycache.provider.CacheProvider;
import easycache.provider.ObjectLevelTimer;
import easycache.utils.PropertiesUtils;

public class SpyProvider implements CacheProvider, ObjectLevelTimer, CacheConfig {

    protected static MemcachedClient cache;
    private final static Logger logger = Logger.getLogger(DangaProvider.class);
    // key:url value:url 便于删除和增加
    private static final Map<String, String> servers = new HashMap<String, String>();

    static {
        try {
            init();
            Runtime.getRuntime().addShutdownHook(new Thread() {

                @Override
                public void run() {
                    cache.shutdown();
                }
            });
        } catch (final FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (final IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected static void init() throws FileNotFoundException, IOException {
        PropertiesUtils.load("memcached.properties");
        for (final String s : PropertiesUtils.getValue("memcached.servers").split(",")) {
            servers.put(s, s);
        }
        cache = new MemcachedClient(AddrUtil.getAddresses(new ArrayList<String>(servers.keySet())));
    }

    @Override
    public void buildCache() {
    }

    @Override
    public Map<String, Object> get(final String[] keys) {
        return cache.getBulk(keys);
    }

    @Override
    public Object get(final String key) {
        return cache.get(key);
    }

    @Override
    public boolean put(final String key, final Object value) {
        try {
            return cache.set(key, 0, value).get();
        } catch (final InterruptedException e) {
            logger.error(e.getMessage(), e);
        } catch (final ExecutionException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean put(final String key, final Object value, final Date expiry) {
        try {
            return cache.set(key, (int) (expiry.getTime() / 1000), value).get();
        } catch (final InterruptedException e) {
            logger.error(e.getMessage(), e);
        } catch (final ExecutionException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean put(final String key, final Object value, final int expirySec) {
        try {
            return cache.set(key, expirySec, value).get();
        } catch (final InterruptedException e) {
            logger.error(e.getMessage(), e);
        } catch (final ExecutionException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean remove(final String key) {
        try {
            return cache.delete(key).get();
        } catch (final InterruptedException e) {
            logger.error(e.getMessage(), e);
        } catch (final ExecutionException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public void addServer(final String ip, final int weights) {
        servers.put(ip, ip);
        cache.shutdown();
        cache = null;
        try {
            cache = new MemcachedClient(AddrUtil.getAddresses(new ArrayList<String>(servers.keySet())));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeServer(final String ip) {
        servers.remove(ip);
        cache.shutdown();
        cache = null;
        try {
            cache = new MemcachedClient(AddrUtil.getAddresses(new ArrayList<String>(servers.keySet())));
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String statistics() {
        return cache.getStats().toString();
    }
}
