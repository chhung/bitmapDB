package com.zk.bitmapDB.web.port;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BitmapAddRequest {

    @NotBlank(message = "key must not be blank")
    private String key;

    @NotNull(message = "value must not be null")
    @Min(value = 0, message = "value must be >= 0")
    private Long value;

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }

    public Long getValue() { return value; }
    public void setValue(Long value) { this.value = value; }
}

