package com.zk.bitmapDB.repository;
import com.zk.bitmapDB.application.port.IssueCouponRedis;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.ByteArrayCodec;
import org.springframework.stereotype.Repository;
/**
 * Redis implementation for storing RoaringBitmap bytes.
 */
@Repository
public class IssueCouponRedisRepo implements IssueCouponRedis {
    private final RedissonClient redisson;
    public IssueCouponRedisRepo(RedissonClient redisson) {
        this.redisson = redisson;
    }
    @Override
    public void writeBitmap(String key, byte[] bitmapBytes) {
        RBucket<byte[]> bucket = redisson.getBucket(key, ByteArrayCodec.INSTANCE);
        bucket.set(bitmapBytes);
    }

    @Override
    public byte[] readBitmap(String key) {
        RBucket<byte[]> bucket = redisson.getBucket(key, ByteArrayCodec.INSTANCE);
        return bucket.get();
    }
}