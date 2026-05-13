package com.zk.bitmapDB.application;
import com.zk.bitmapDB.application.port.RedisOperation;
import org.springframework.stereotype.Service;
@Service
public class RedisOperationService {
    private final RedisOperation repo;
    public RedisOperationService(RedisOperation repo) {
        this.repo = repo;
    }
    /**
     * Converts the value based on type and delegates to the repository.
     *
     * @param key   Redis key
     * @param type  "string" or "number"
     * @param value raw string value
     * @param ttl   TTL in seconds, nullable
     * @throws IllegalArgumentException if type is "number" but value cannot be parsed
     */
    public void write(String key, String type, String value, Long ttl) {
        Object converted = convertValue(type, value);
        repo.write(key, converted, ttl);
    }
    public Object read(String key) {
        return repo.read(key);
    }
    private Object convertValue(String type, String value) {
        if ("number".equals(type)) {
            try {
                if (value.contains(".")) {
                    return Double.parseDouble(value);
                }
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Value '" + value + "' cannot be converted to number", e);
            }
        }
        // type == "string"
        return value;
    }
}
