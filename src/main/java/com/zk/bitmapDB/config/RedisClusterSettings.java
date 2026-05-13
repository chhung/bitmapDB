package com.zk.bitmapDB.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Binds {@code app.redis-cluster.*} entries from application properties.
 * Used when {@code --app.redis.mode=cluster} is specified.
 */
@ConfigurationProperties(prefix = "app.redis-cluster")
public class RedisClusterSettings {

    /**
     * Comma-separated cluster node addresses,
     * e.g. redis://127.0.0.1:7001,redis://127.0.0.1:7002,redis://127.0.0.1:7003
     */
    private List<String> nodeAddresses = new ArrayList<>();

    /** Redis password (leave blank for no auth). */
    private String password;

    /** Cluster scan interval in milliseconds. */
    private int scanInterval = 2000;

    /** Minimum idle connections to master nodes. */
    private int masterConnectionMinimumIdleSize = 4;

    /** Maximum connection pool size to master nodes. */
    private int masterConnectionPoolSize = 16;

    public List<String> getNodeAddresses() {
        return nodeAddresses;
    }

    public void setNodeAddresses(List<String> nodeAddresses) {
        this.nodeAddresses = nodeAddresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScanInterval() {
        return scanInterval;
    }

    public void setScanInterval(int scanInterval) {
        this.scanInterval = scanInterval;
    }

    public int getMasterConnectionMinimumIdleSize() {
        return masterConnectionMinimumIdleSize;
    }

    public void setMasterConnectionMinimumIdleSize(int masterConnectionMinimumIdleSize) {
        this.masterConnectionMinimumIdleSize = masterConnectionMinimumIdleSize;
    }

    public int getMasterConnectionPoolSize() {
        return masterConnectionPoolSize;
    }

    public void setMasterConnectionPoolSize(int masterConnectionPoolSize) {
        this.masterConnectionPoolSize = masterConnectionPoolSize;
    }

    public boolean hasPassword() {
        return password != null && !password.isBlank();
    }
}

