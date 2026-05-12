package com.zk.bitmapDB.repository;

import com.zk.bitmapDB.application.dto.CouponId;
import com.zk.bitmapDB.application.port.UseCoupon;
import com.zk.bitmapDB.domain.Coupon;
import org.springframework.stereotype.Repository;

@Repository
public class UseCouponRepo implements UseCoupon {
  public Coupon find(CouponId couponId) {
    return null;
  }

  public void save(Coupon c) {
  }
}
