package com.zk.bitmapDB.web;

import com.zk.bitmapDB.application.CreateCouponService;
import com.zk.bitmapDB.domain.CouponM;
import com.zk.bitmapDB.web.port.CreateCouponRequest;
import com.zk.bitmapDB.web.port.CreateCouponResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateCouponController {

    private final CreateCouponService service;

    public CreateCouponController(CreateCouponService service) {
        this.service = service;
    }

    @PostMapping("/coupons/create")
    public CreateCouponResponse create(@RequestBody @Valid CreateCouponRequest req) {
        CouponM coupon = new CouponM(req.getCpCode());
        String id = service.create(coupon);
        return new CreateCouponResponse(id);
    }
}

