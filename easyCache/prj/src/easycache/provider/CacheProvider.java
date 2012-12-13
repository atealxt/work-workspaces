package easycache.provider;

public interface CacheProvider {

    void buildCache();

    Object get(String key);

    boolean put(String key, Object value);

    boolean remove(String key);

    String statistics();
}
