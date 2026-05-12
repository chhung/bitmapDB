package com.zk.bitmapDB.application.port;

import com.zk.bitmapDB.application.dto.CouponId;
import com.zk.bitmapDB.domain.Coupon;

public interface UseCoupon {
  Coupon find(CouponId couponId);
  void save(Coupon c);
}
