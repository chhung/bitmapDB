package com.zk.bitmapDB.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creates a {@link RedissonClient} backed by a Redis Cluster.
 * <p>Activated when {@code --app.redis.mode=cluster} is passed as a program argument.</p>
 *
 * <p>Inject in your service:
 * <pre>
 *   public MyService(@Qualifier("redisCluster") RedissonClient redisson) { ... }
 * </pre>
 */
@Configuration
@ConditionalOnProperty(name = "app.redis.mode", havingValue = "cluster")
@EnableConfigurationProperties(RedisClusterSettings.class)
public class RedisClusterConfig {

    private final RedisClusterSettings settings;

    public RedisClusterConfig(RedisClusterSettings settings) {
        this.settings = settings;
    }

    @Bean(name = "redisCluster", destroyMethod = "shutdown")
    @Qualifier("redisCluster")
    public RedissonClient redisClusterClient() {
        Config config = new Config();
        var clusterConfig = config.useClusterServers()
                .setScanInterval(settings.getScanInterval())
                .setMasterConnectionMinimumIdleSize(settings.getMasterConnectionMinimumIdleSize())
                .setMasterConnectionPoolSize(settings.getMasterConnectionPoolSize());

        // Add all node addresses
        for (String nodeAddress : settings.getNodeAddresses()) {
            clusterConfig.addNodeAddress(nodeAddress.trim());
        }

        if (settings.hasPassword()) {
            clusterConfig.setPassword(settings.getPassword());
        }

        return Redisson.create(config);
    }
}

