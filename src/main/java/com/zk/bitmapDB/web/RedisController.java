package com.zk.bitmapDB.web;
import com.zk.bitmapDB.application.RedisOperationService;
import com.zk.bitmapDB.web.port.RedisReadRequest;
import com.zk.bitmapDB.web.port.RedisReadResponse;
import com.zk.bitmapDB.web.port.RedisWriteRequest;
import com.zk.bitmapDB.web.port.RedisWriteResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
@RestController
@RequestMapping("/redis")
public class RedisController {
    private final RedisOperationService service;
    public RedisController(RedisOperationService service) {
        this.service = service;
    }
    /**
     * Write a key-value pair to Redis.
     * <pre>
     * POST /redis/write
     * {
     *   "key": "myKey",
     *   "type": "string",   // "string" or "number"
     *   "value": "hello",
     *   "ttl": 60           // optional, seconds
     * }
     * </pre>
     */
    @PostMapping("/write")
    public RedisWriteResponse write(@RequestBody @Valid RedisWriteRequest req) {
        try {
            service.write(req.getKey(), req.getType(), req.getValue(), req.getTtl());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        String ttlMsg = req.getTtl() != null ? " with TTL " + req.getTtl() + "s" : "";
        return new RedisWriteResponse(req.getKey(), "OK" + ttlMsg);
    }
    /**
     * Read a value from Redis by key.
     * <pre>
     * POST /redis/read
     * {
     *   "key": "myKey"
     * }
     * </pre>
     */
    @PostMapping("/read")
    public RedisReadResponse read(@RequestBody @Valid RedisReadRequest req) {
        Object value = service.read(req.getKey());
        boolean exists = value != null;
        return new RedisReadResponse(req.getKey(), value, exists);
    }
}
