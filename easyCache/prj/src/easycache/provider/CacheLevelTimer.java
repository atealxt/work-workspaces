package easycache.provider;

/** 支持缓存工具级别粒度的超时控制，如ehcached */
public interface CacheLevelTimer extends CacheProvider {

    void buildCache(final String cacheName);
}
