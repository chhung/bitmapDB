package com.zk.bitmapDB.application;

import com.zk.bitmapDB.application.port.CreateCoupon;
import com.zk.bitmapDB.domain.CouponM;
import org.springframework.stereotype.Service;

@Service
public class CreateCouponService {

    private final CreateCoupon repo;

    public CreateCouponService(CreateCoupon repo) {
        this.repo = repo;
    }

    public String create(CouponM coupon) {
        return repo.create(coupon);
    }
}

