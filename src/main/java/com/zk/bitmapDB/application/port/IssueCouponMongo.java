package com.zk.bitmapDB.application.port;
/**
 * Port for persisting RoaringBitmap data to MongoDB issueMap collection.
 */
public interface IssueCouponMongo {
    /**
     * Upsert the bitmap byte array for a given custNo.
     * If the document already exists, merge the new bitmap into the existing one.
     *
     * @param custNo   customer number (numeric)
     * @param bitmapBytes serialized RoaringBitmap byte array
     */
    void upsertBitmap(long custNo, byte[] bitmapBytes);
    /**
     * Find existing bitmap bytes for a custNo, or null if not found.
     */
    byte[] findBitmap(long custNo);
}