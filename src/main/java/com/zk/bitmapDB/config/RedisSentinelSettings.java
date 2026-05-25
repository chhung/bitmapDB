package com.zk.bitmapDB.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Binds {@code app.redis-sentinel.*} entries from application properties.
 * Used when {@code --app.redis.mode=sentinel} is specified.
 */
@ConfigurationProperties(prefix = "app.redis-sentinel")
public class RedisSentinelSettings {

    /** Sentinel master name, e.g. "mymaster" */
    private String masterName = "mymaster";

    /**
     * Sentinel node addresses,
     * e.g. redis://127.0.0.1:26379,redis://127.0.0.1:26380,redis://127.0.0.1:26381
     */
    private List<String> sentinelAddresses = new ArrayList<>();

    /** Redis password for data nodes (leave blank for no auth). */
    private String password;

    /** Redis password for sentinel nodes (leave blank for no auth). */
    private String sentinelPassword;

    /** Redis database index. */
    private int database = 0;

    /** Minimum idle connections in the pool. */
    private int connectionMinimumIdleSize = 4;

    /** Maximum connection pool size. */
    private int connectionPoolSize = 16;

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public List<String> getSentinelAddresses() {
        return sentinelAddresses;
    }

    public void setSentinelAddresses(List<String> sentinelAddresses) {
        this.sentinelAddresses = sentinelAddresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSentinelPassword() {
        return sentinelPassword;
    }

    public void setSentinelPassword(String sentinelPassword) {
        this.sentinelPassword = sentinelPassword;
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

    public boolean hasSentinelPassword() {
        return sentinelPassword != null && !sentinelPassword.isBlank();
    }
}

