package easycache.provider;

import java.util.Date;
import java.util.Map;

/** 支持对象粒度的超时控制，如memcached */
public interface ObjectLevelTimer extends CacheProvider {

    /** 一次获取多个值 */
    Map<String, Object> get(final String keys[]);

    /** @param expirySec 过期时间(经过{expiry}秒后失效，最大为30*24*60*60) */
    boolean put(String key, Object value, int expirySec);

    /** @param expiry 过期时间(到达{expiry}后失效) */
    boolean put(String key, Object value, Date expiry);
}
