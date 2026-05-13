package com.zk.bitmapDB.web.port;
import jakarta.validation.constraints.NotBlank;
public class RedisReadRequest {
    @NotBlank(message = "key is required")
    private String key;
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
}
