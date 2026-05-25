package com.zk.bitmapDB.application;
import com.zk.bitmapDB.application.port.IssueCouponMongo;
import com.zk.bitmapDB.application.port.IssueCouponRedis;
import org.roaringbitmap.RoaringBitmap;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/**
 * Application service for issuing coupons.
 * Composes two independent infrastructure operations (MongoDB + Redis)
 * to satisfy the Single Responsibility Principle.
 */
@Service
public class IssueCouponService {
    private static final long CP_CODE_OFFSET = 200500000000L;
    private final IssueCouponMongo mongoPort;
    private final IssueCouponRedis redisPort;
    public IssueCouponService(IssueCouponMongo mongoPort, IssueCouponRedis redisPort) {
        this.mongoPort = mongoPort;
        this.redisPort = redisPort;
    }

    /**
     * Encode a cpCode string to a bitmap integer value.
     *
     * @param cpCode coupon code string (e.g. "202211210121")
     * @return unsigned 32-bit integer value for RoaringBitmap
     */
    public int encodeCpCode(String cpCode) {
        long cpCodeLong = Long.parseLong(cpCode);
        long bitmapValueLong = cpCodeLong - CP_CODE_OFFSET;
        if (bitmapValueLong < 0 || bitmapValueLong > 4_294_967_295L) {
            throw new IllegalArgumentException(
                    "cpCode after offset must be in range [0, 4294967295], got: " + bitmapValueLong);
        }
        return (int) bitmapValueLong;
    }

    /**
     * Issue multiple coupons to a single customer.
     *
     * @param cpCodes      list of coupon code strings
     * @param issueCustNo  single customer number string
     * @return list of bitmap integer values that were added
     */
    public List<Integer> issue(List<String> cpCodes, String issueCustNo) {
        long custNo = Long.parseLong(issueCustNo);

        // 1. Load existing bitmap
        RoaringBitmap bitmap = loadOrCreateBitmap(custNo);

        // 2. Encode each cpCode and add to bitmap
        List<Integer> bitmapValues = new ArrayList<>();
        for (String cpCode : cpCodes) {
            int bitmapValue = encodeCpCode(cpCode);
            bitmap.add(bitmapValue);
            bitmapValues.add(bitmapValue);
        }

        // 3. Serialize once
        byte[] bitmapBytes = serializeBitmap(bitmap);

        // 4. Write to MongoDB (upsert)
        mongoPort.upsertBitmap(custNo, bitmapBytes);

        // 5. Write to Redis
        String redisKey = "user:" + issueCustNo + ":received";
        redisPort.writeBitmap(redisKey, bitmapBytes);

        return bitmapValues;
    }
    private RoaringBitmap loadOrCreateBitmap(long custNo) {
        byte[] existing = mongoPort.findBitmap(custNo);
        if (existing == null || existing.length == 0) {
            return new RoaringBitmap();
        }
        RoaringBitmap bitmap = new RoaringBitmap();
        try {
            bitmap.deserialize(ByteBuffer.wrap(existing));
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize existing bitmap for custNo: " + custNo, e);
        }
        return bitmap;
    }
    private byte[] serializeBitmap(RoaringBitmap bitmap) {
        bitmap.runOptimize();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (DataOutputStream dos = new DataOutputStream(baos)) {
            bitmap.serialize(dos);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize bitmap", e);
        }
        return baos.toByteArray();
    }
}