package com.zk.bitmapDB.web.port;

public class BitmapAddResponse {

    private final String key;
    private final long value;
    private final long cardinality;

    public BitmapAddResponse(String key, long value, long cardinality) {
        this.key = key;
        this.value = value;
        this.cardinality = cardinality;
    }

    public String getKey() { return key; }
    public long getValue() { return value; }
    /** Total number of distinct values stored in this bitmap. */
    public long getCardinality() { return cardinality; }
}

