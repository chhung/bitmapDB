package com.zk.bitmapDB.web.port;

import jakarta.validation.constraints.NotBlank;

public class BitmapGetRequest {

    @NotBlank(message = "key must not be blank")
    private String key;

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
}

