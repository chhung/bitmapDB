package com.zk.bitmapDB.application.port;
public interface RedisOperation {
    /**
     * Write a value to Redis.
     * @param key   the Redis key
     * @param value the value (already type-converted)
     * @param ttl   TTL in seconds, null means no expiration
     */
    void write(String key, Object value, Long ttl);
    /**
     * Read a value from Redis by key.
     * @param key the Redis key
     * @return the stored value, or null if the key does not exist
     */
    Object read(String key);
}
