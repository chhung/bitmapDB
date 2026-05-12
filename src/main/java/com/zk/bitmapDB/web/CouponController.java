package com.zk.bitmapDB.web;

import com.zk.bitmapDB.application.UseCouponService;
import com.zk.bitmapDB.application.dto.CouponId;
import com.zk.bitmapDB.application.dto.UserId;
import com.zk.bitmapDB.web.port.UseCouponRequest;
import com.zk.bitmapDB.web.port.UseCouponResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

  private final UseCouponService service;

  public  CouponController(UseCouponService service) {
    this.service = service;
  }

  @PostMapping("/coupons/use")
  public UseCouponResponse use(@RequestBody @Valid UseCouponRequest req) {

    String userId = req.getUserId();
    String couponId = req.getCouponId();
    var result = service.use( new UserId(userId), new CouponId(couponId));

    return new UseCouponResponse(result);
  }
}
