package com.zk.bitmapDB.web.port;


public class UseCouponRequest {
  private String couponId;
  private String userId;

  public String getCouponId() {
    return couponId;
  }

  public void setCouponId(String couponId) {
    this.couponId = couponId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

}
