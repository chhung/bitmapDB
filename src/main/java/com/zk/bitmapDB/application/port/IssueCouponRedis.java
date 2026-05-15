package com.zk.bitmapDB.application.port;
/**
 * Port for persisting RoaringBitmap data to Redis.
 */
public interface IssueCouponRedis {
    /**
     * Write bitmap bytes to Redis under the given key.
     *
     * @param key          Redis key (e.g. "user:201412906511:received")
     * @param bitmapBytes  serialized RoaringBitmap byte array
     */
    void writeBitmap(String key, byte[] bitmapBytes);

    /**
     * Read bitmap bytes from Redis.
     *
     * @param key Redis key
     * @return serialized RoaringBitmap byte array, or null if key does not exist
     */
    byte[] readBitmap(String key);
}