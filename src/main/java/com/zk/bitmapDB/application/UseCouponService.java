package com.zk.bitmapDB.application;

import com.zk.bitmapDB.application.dto.CouponId;
import com.zk.bitmapDB.application.dto.UserId;
import com.zk.bitmapDB.application.port.UseCoupon;
import com.zk.bitmapDB.domain.Coupon;
import org.springframework.stereotype.Service;

@Service
public class UseCouponService {

  private final UseCoupon repo;

  public UseCouponService(UseCoupon repo) {
    this.repo = repo;
  }

  public Object use(UserId userId, CouponId couponId) {
    Coupon c = repo.find(couponId);
    String id = userId.getId();
    c.use(id);
    repo.save(c);

    return null;
  }
}
