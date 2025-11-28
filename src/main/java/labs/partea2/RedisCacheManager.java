package labs.partea2;

import redis.clients.jedis.Jedis;

public class RedisCacheManager {
    private final Jedis jedis;

    public RedisCacheManager() {
        String host = System.getenv().getOrDefault("REDIS_HOST", "localhost");
        int port = Integer.parseInt(System.getenv().getOrDefault("REDIS_PORT", "6379"));

        this.jedis = new Jedis(host, port);
    }

    public String get(String key) {
        return jedis.get(key);
    }

    public void set(String key, String value, int ttlSeconds) {
        jedis.setex(key, ttlSeconds, value);
    }

    public void invalidateAll() {
        jedis.flushAll();
    }
}
