package com.zk.bitmapDB.web.port;

public class CreateCouponResponse {

    private String id;

    public CreateCouponResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

