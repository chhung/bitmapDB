package com.zk.bitmapDB.web.port;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RedisWriteRequest {

    @NotBlank(message = "key is required")
    private String key;

    @NotBlank(message = "type is required, must be 'string' or 'number'")
    @Pattern(regexp = "string|number", message = "type must be 'string' or 'number'")
    private String type;

    @NotBlank(message = "value is required")
    private String value;

    /** TTL in seconds. Null or absent means no expiration. */
    private Long ttl;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }
}

