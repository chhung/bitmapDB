package com.zk.bitmapDB.repository;
import com.zk.bitmapDB.application.port.RedisOperation;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;
import java.time.Duration;
@Repository
public class RedisOperationRepo implements RedisOperation {
    private final RedissonClient redisson;
    public RedisOperationRepo(RedissonClient redisson) {
        this.redisson = redisson;
    }
    @Override
    public void write(String key, Object value, Long ttl) {
        RBucket<Object> bucket = redisson.getBucket(key);
        if (ttl != null && ttl > 0) {
            bucket.set(value, Duration.ofSeconds(ttl));
        } else {
            bucket.set(value);
        }
    }
    @Override
    public Object read(String key) {
        RBucket<Object> bucket = redisson.getBucket(key);
        return bucket.get();
    }
}
