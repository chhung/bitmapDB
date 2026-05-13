package com.zk.bitmapDB.application;

import org.roaringbitmap.RoaringBitmap;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory RoaringBitmap store.
 * Each key maps to its own bitmap instance.
 */
@Service
public class BitmapService {

    private final ConcurrentHashMap<String, RoaringBitmap> store = new ConcurrentHashMap<>();

    /**
     * Add a value to the bitmap identified by key.
     *
     * @param key   bitmap key
     * @param value unsigned 32-bit integer to add
     */
    public void add(String key, long value) {
        if (value < 0 || value > 4_294_967_295L) {
            throw new IllegalArgumentException(
                    "value must be in range [0, 4294967295], got: " + value);
        }
        store.computeIfAbsent(key, k -> new RoaringBitmap())
             .add((int) value);
    }

    /**
     * Check whether a value exists in the bitmap identified by key.
     *
     * @param key   bitmap key
     * @param value value to check
     * @return true if the bitmap contains the value
     */
    public boolean contains(String key, long value) {
        RoaringBitmap bm = store.get(key);
        if (bm == null) return false;
        return bm.contains((int) value);
    }

    /**
     * Return the cardinality (number of distinct values) of the bitmap.
     *
     * @param key bitmap key
     * @return number of values, 0 if key does not exist
     */
    public long cardinality(String key) {
        RoaringBitmap bm = store.get(key);
        return bm == null ? 0L : bm.getLongCardinality();
    }

    /**
     * Return all values stored in the bitmap identified by key.
     *
     * @param key bitmap key
     * @return array of all integer values; empty array if key does not exist
     */
    public long[] getValues(String key) {
        RoaringBitmap bm = store.get(key);
        if (bm == null) return new long[0];
        return bm.stream().asLongStream().toArray();
    }
}

