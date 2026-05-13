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
 * Creates a {@link RedissonClient} backed by a single Redis server.
 * <p>Activated when {@code --app.redis.mode=single} is passed as a program argument.</p>
 *
 * <p>Inject in your service:
 * <pre>
 *   public MyService(@Qualifier("redisSingle") RedissonClient redisson) { ... }
 * </pre>
 */
@Configuration
@ConditionalOnProperty(name = "app.redis.mode", havingValue = "single")
@EnableConfigurationProperties(RedisSingleSettings.class)
public class RedisSingleConfig {

    private final RedisSingleSettings settings;

    public RedisSingleConfig(RedisSingleSettings settings) {
        this.settings = settings;
    }

    @Bean(name = "redisSingle", destroyMethod = "shutdown")
    @Qualifier("redisSingle")
    public RedissonClient redisSingleClient() {
        Config config = new Config();
        var serverConfig = config.useSingleServer()
                .setAddress(settings.getAddress())
                .setDatabase(settings.getDatabase())
                .setConnectionMinimumIdleSize(settings.getConnectionMinimumIdleSize())
                .setConnectionPoolSize(settings.getConnectionPoolSize());

        if (settings.hasPassword()) {
            serverConfig.setPassword(settings.getPassword());
        }

        return Redisson.create(config);
    }
}

