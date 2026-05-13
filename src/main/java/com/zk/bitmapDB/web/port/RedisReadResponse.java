package com.zk.bitmapDB.web.port;
public class RedisReadResponse {
    private String key;
    private Object value;
    private boolean exists;
    public RedisReadResponse(String key, Object value, boolean exists) {
        this.key = key;
        this.value = value;
        this.exists = exists;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public boolean isExists() {
        return exists;
    }
    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
