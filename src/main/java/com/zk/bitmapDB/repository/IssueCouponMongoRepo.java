package com.zk.bitmapDB.repository;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.zk.bitmapDB.application.port.IssueCouponMongo;
import org.bson.Document;
import org.bson.types.Binary;
import org.springframework.stereotype.Repository;
/**
 * MongoDB implementation for issueMap collection.
 * Document schema: { custNo: Long, received: Binary (RoaringBitmap bytes) }
 */
@Repository
public class IssueCouponMongoRepo implements IssueCouponMongo {
    private static final String COLLECTION = "issueMap";
    private final MongoDatabase database;
    public IssueCouponMongoRepo(MongoDatabase database) {
        this.database = database;
    }
    @Override
    public void upsertBitmap(long custNo, byte[] bitmapBytes) {
        Document doc = new Document("custNo", custNo)
                .append("received", new Binary(bitmapBytes));
        database.getCollection(COLLECTION).replaceOne(
                Filters.eq("custNo", custNo),
                doc,
                new ReplaceOptions().upsert(true)
        );
    }
    @Override
    public byte[] findBitmap(long custNo) {
        Document doc = database.getCollection(COLLECTION)
                .find(Filters.eq("custNo", custNo))
                .first();
        if (doc == null) {
            return null;
        }
        Binary binary = doc.get("received", Binary.class);
        return binary == null ? null : binary.getData();
    }
}