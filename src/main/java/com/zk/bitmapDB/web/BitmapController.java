package com.zk.bitmapDB.web;

import com.zk.bitmapDB.application.BitmapService;
import com.zk.bitmapDB.web.port.BitmapAddRequest;
import com.zk.bitmapDB.web.port.BitmapAddResponse;
import com.zk.bitmapDB.web.port.BitmapGetRequest;
import com.zk.bitmapDB.web.port.BitmapGetResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/bitmap")
public class BitmapController {

    private final BitmapService service;

    public BitmapController(BitmapService service) {
        this.service = service;
    }

    /**
     * Add a value to the RoaringBitmap identified by the given key.
     *
     * <pre>
     * POST /bitmap/add
     * {
     *   "key":   "myBitmap",
     *   "value": 42
     * }
     * </pre>
     *
     * Response:
     * <pre>
     * {
     *   "key":         "myBitmap",
     *   "value":       42,
     *   "cardinality": 1
     * }
     * </pre>
     */
    @PostMapping("/add")
    public BitmapAddResponse add(@RequestBody @Valid BitmapAddRequest req) {
        try {
            service.add(req.getKey(), req.getValue());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
        long cardinality = service.cardinality(req.getKey());
        return new BitmapAddResponse(req.getKey(), req.getValue(), cardinality);
    }

    /**
     * Retrieve all values stored in the RoaringBitmap identified by the given key.
     *
     * <pre>
     * POST /bitmap/get
     * {
     *   "key": "myBitmap"
     * }
     * </pre>
     *
     * Response:
     * <pre>
     * {
     *   "key":         "myBitmap",
     *   "values":      [42, 100, 200],
     *   "cardinality": 3
     * }
     * </pre>
     */
    @PostMapping("/get")
    public BitmapGetResponse get(@RequestBody @Valid BitmapGetRequest req) {
        long[] values = service.getValues(req.getKey());
        long cardinality = service.cardinality(req.getKey());
        return new BitmapGetResponse(req.getKey(), values, cardinality);
    }
}

