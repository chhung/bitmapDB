package com.zk.bitmapDB.web.port;

public class UseCouponResponse {

  private String couponId;
  private String userId;
  private String status;

  public UseCouponResponse(Object result) {}

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
  }
}