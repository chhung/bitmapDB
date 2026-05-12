package com.zk.bitmapDB.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Builds two independent MongoDB connections (primary + secondary) using the
 * official driver. Inject by qualifier when both are needed:
 * <pre>
 *   public CouponService(@Qualifier("primaryMongoDatabase") MongoDatabase db) { ... }
 * </pre>
 */
@Configuration
@EnableConfigurationProperties(MongoSettings.class)
public class MongoConfig {

    private final MongoSettings settings;

    public MongoConfig(MongoSettings settings) {
        this.settings = settings;
    }

    // --- Primary -----------------------------------------------------------

    @Primary
    @Bean(destroyMethod = "close")
    public MongoClient primaryMongoClient() {
        return buildClient(settings.getPrimary());
    }

    @Primary
    @Bean
    public MongoDatabase primaryMongoDatabase(MongoClient primaryMongoClient) {
        return primaryMongoClient.getDatabase(settings.getPrimary().getDatabase());
    }

    // --- Secondary ---------------------------------------------------------

    /*@Bean(name = "secondaryMongoClient", destroyMethod = "close")
    public MongoClient secondaryMongoClient() {
        return buildClient(settings.getSecondary());
    }

    @Bean(name = "secondaryMongoDatabase")
    public MongoDatabase secondaryMongoDatabase(
            @org.springframework.beans.factory.annotation.Qualifier("secondaryMongoClient") MongoClient secondaryMongoClient) {
        return secondaryMongoClient.getDatabase(settings.getSecondary().getDatabase());
    }*/

    // --- Helpers -----------------------------------------------------------

    private MongoClient buildClient(MongoSettings.Connection conn) {
        if (conn == null || conn.getUri() == null || conn.getUri().isBlank()) {
            throw new IllegalStateException("MongoDB connection URI is not configured");
        }
        MongoClientSettings.Builder builder = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(conn.getUri()));

        if (conn.hasCredentials()) {
            MongoCredential credential = MongoCredential.createCredential(
                    conn.getUsername(),
                    conn.getAuthDatabase(),
                    conn.getPassword().toCharArray());
            builder.credential(credential);
        }

        return MongoClients.create(builder.build());
    }
}

