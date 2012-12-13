package easycache.provider;

public interface CacheConfig {

    void addServer(String ip, int weights);

    void removeServer(String ip);
}
