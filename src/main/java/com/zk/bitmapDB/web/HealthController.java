package com.zk.bitmapDB.web;

import java.util.LinkedHashMap;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoDatabase;

/**
 * Quick health endpoint that pings both Mongo databases so we can verify the
 * RESTful + driver wiring end-to-end.
 *
 * <p>GET /api/health -&gt; {"status":"UP", "primary":"UP", "secondary":"UP"}
 */
@RestController
@RequestMapping("/api")
public class HealthController {

    private final MongoDatabase primary;
    //private final MongoDatabase secondary;

    public HealthController(
            @Qualifier("primaryMongoDatabase") MongoDatabase primary/*,
            @Qualifier("secondaryMongoDatabase") MongoDatabase secondary*/) {
        this.primary = primary;
        //this.secondary = secondary;
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "UP");
        body.put("primary", ping(primary));
        //body.put("secondary", ping(secondary));
        return body;
    }

    private String ping(MongoDatabase db) {
        try {
            db.runCommand(new Document("ping", 1));
            return "UP";
        } catch (RuntimeException ex) {
            return "DOWN: " + ex.getMessage();
        }
    }
}

