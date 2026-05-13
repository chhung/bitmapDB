package com.zk.bitmapDB.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Binds {@code app.redis-single.*} entries from application properties.
 * Used when {@code --app.redis.mode=single} is specified.
 */
@ConfigurationProperties(prefix = "app.redis-single")
public class RedisSingleSettings {

    /** Redis address, e.g. redis://127.0.0.1:6379 */
    private String address = "redis://127.0.0.1:6379";

    /** Redis password (leave blank for no auth). */
    private String password;

    /** Redis database index. */
    private int database = 0;

    /** Minimum idle connections in the pool. */
    private int connectionMinimumIdleSize = 4;

    /** Maximum connection pool size. */
    private int connectionPoolSize = 16;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public int getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public void setConnectionMinimumIdleSize(int connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public boolean hasPassword() {
        return password != null && !password.isBlank();
    }
}

