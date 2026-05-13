package com.zk.bitmapDB.repository;
import com.zk.bitmapDB.application.port.RedisOperation;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
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
        // 使用 StringCodec 儲存純文字，避免 Redisson 預設序列化產生亂碼
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        String strValue = String.valueOf(value);
        if (ttl != null && ttl > 0) {
            bucket.set(strValue, Duration.ofSeconds(ttl));
        } else {
            bucket.set(strValue);
        }
    }
    @Override
    public Object read(String key) {
        RBucket<String> bucket = redisson.getBucket(key, StringCodec.INSTANCE);
        return bucket.get();
    }
}
