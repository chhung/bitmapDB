package com.zk.bitmapDB.application;
import com.zk.bitmapDB.application.port.IssueCouponMongo;
import com.zk.bitmapDB.application.port.IssueCouponRedis;
import org.roaringbitmap.RoaringBitmap;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.roaringbitmap.IntConsumer;
/**
 * Query all received coupons for a customer.
 * Strategy: Redis first, fallback to MongoDB, then backfill Redis.
 */
@Service
public class QueryReceivedCouponsService {
    private static final long CP_CODE_OFFSET = 200500000000L;
    private final IssueCouponRedis redisPort;
    private final IssueCouponMongo mongoPort;
    public QueryReceivedCouponsService(IssueCouponRedis redisPort, IssueCouponMongo mongoPort) {
        this.redisPort = redisPort;
        this.mongoPort = mongoPort;
    }
    /**
     * Query all cpCodes that a customer has received.
     *
     * @param custNo customer number string (e.g. "201412906511")
     * @return list of cpCode strings
     */
    public List<String> query(String custNo) {
        String redisKey = "user:" + custNo + ":received";
        long custNoLong = Long.parseLong(custNo);
        // 1. Try Redis first
        byte[] bitmapBytes = redisPort.readBitmap(redisKey);
        // 2. Fallback to MongoDB if Redis has no data
        if (bitmapBytes == null || bitmapBytes.length == 0) {
            bitmapBytes = mongoPort.findBitmap(custNoLong);
            // No data at all
            if (bitmapBytes == null || bitmapBytes.length == 0) {
                return Collections.emptyList();
            }
            // Backfill Redis
            redisPort.writeBitmap(redisKey, bitmapBytes);
        }
        // 3. Deserialize bitmap and convert back to cpCodes
        RoaringBitmap bitmap = deserialize(bitmapBytes, custNoLong);
        return toCpCodes(bitmap);
    }
    private RoaringBitmap deserialize(byte[] bytes, long custNo) {
        RoaringBitmap bitmap = new RoaringBitmap();
        try {
            bitmap.deserialize(ByteBuffer.wrap(bytes));
        } catch (IOException e) {
            throw new RuntimeException("Failed to deserialize bitmap for custNo: " + custNo, e);
        }
        return bitmap;
    }
    private List<String> toCpCodes(RoaringBitmap bitmap) {
        List<String> cpCodes = new ArrayList<>();
        bitmap.forEach((IntConsumer) value -> {
            long cpCodeLong = Integer.toUnsignedLong(value) + CP_CODE_OFFSET;
            cpCodes.add(String.valueOf(cpCodeLong));
        });
        return cpCodes;
    }
}