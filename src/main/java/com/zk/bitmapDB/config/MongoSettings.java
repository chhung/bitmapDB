package com.zk.bitmapDB.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Binds {@code app.mongodb.*} entries from application.properties.
 * Each nested {@link Connection} represents a single MongoDB instance/database.
 */
@ConfigurationProperties(prefix = "app.mongodb")
public class MongoSettings {

    private Connection primary = new Connection();
    private Connection secondary = new Connection();

    public Connection getPrimary() {
        return primary;
    }

    public void setPrimary(Connection primary) {
        this.primary = primary;
    }

    public Connection getSecondary() {
        return secondary;
    }

    public void setSecondary(Connection secondary) {
        this.secondary = secondary;
    }

    public static class Connection {
        /** Standard MongoDB connection string, e.g. mongodb://host:27017 */
        private String uri;
        /** Logical database name to use on this connection. */
        private String database;
        /** MongoDB username (optional, leave blank for unauthenticated). */
        private String username;
        /** MongoDB password (optional). */
        private String password;
        /** The database used for authentication (default: admin). */
        private String authDatabase = "admin";

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAuthDatabase() {
            return authDatabase;
        }

        public void setAuthDatabase(String authDatabase) {
            this.authDatabase = authDatabase;
        }

        public boolean hasCredentials() {
            return username != null && !username.isBlank()
                && password != null && !password.isBlank();
        }
    }
}

