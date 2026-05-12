package com.zk.bitmapDB.repository;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import com.zk.bitmapDB.application.port.CreateCoupon;
import com.zk.bitmapDB.domain.CouponM;
import org.bson.Document;
import org.springframework.stereotype.Repository;

@Repository
public class CreateCouponRepo implements CreateCoupon {

    private final MongoDatabase database;

    public CreateCouponRepo(MongoDatabase database) {
        this.database = database;
    }

    @Override
    public String create(CouponM coupon) {
        Document doc = new Document("cpCode", coupon.getCpCode());
        InsertOneResult result = database.getCollection("couponM").insertOne(doc);
        return result.getInsertedId().asObjectId().getValue().toHexString();
    }
}

