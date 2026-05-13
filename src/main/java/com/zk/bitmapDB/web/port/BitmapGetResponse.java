package com.zk.bitmapDB.web.port;

public class BitmapGetResponse {

    private final String key;
    private final long[] values;
    private final long cardinality;

    public BitmapGetResponse(String key, long[] values, long cardinality) {
        this.key = key;
        this.values = values;
        this.cardinality = cardinality;
    }

    public String getKey() { return key; }
    public long[] getValues() { return values; }
    public long getCardinality() { return cardinality; }
}

