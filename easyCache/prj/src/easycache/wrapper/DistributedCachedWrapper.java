package easycache.wrapper;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import easycache.CacheException;
import easycache.provider.CacheConfig;
import easycache.provider.ObjectLevelTimer;

public class DistributedCachedWrapper extends CacheWrapper {

    public DistributedCachedWrapper() throws CacheException {}

    public void addServer(final String ip, final int weights) {
        try {
            ((CacheConfig) cache).addServer(ip, weights);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void removeServer(final String ip) {
        try {
            ((CacheConfig) cache).removeServer(ip);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public Map<String, Object> get(final String keys[]) {
        try {
            return ((ObjectLevelTimer) cache).get(keys);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return Collections.emptyMap();
        }
    }

    /** @param expiry 过期时间(到达{expiry}后失效) */
    public boolean put(final String key, final Object value, final Date expiry) {
        try {
            return ((ObjectLevelTimer) cache).put(key, value, expiry);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    /** @param expirySec 过期时间(经过{expiry}秒后失效，最大为30*24*60*60) */
    public boolean put(final String key, final Object value, final int expirySec) {
        try {
            return ((ObjectLevelTimer) cache).put(key, value, expirySec);
        } catch (final Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }
}
