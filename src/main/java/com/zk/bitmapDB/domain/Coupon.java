package com.zk.bitmapDB.domain;

/*
domain才是真正的業務邏輯區域，它包含了業務邏輯的核心概念和規則。它是整個應用程序的核心，負責處理業務邏輯、規則和行為。Domain層通常包含實體（Entities）、值對象（Value Objects）和聚合根（Aggregate Roots）等概念。
它不做跟infra或service layer有關的事情，就是一個類別做好自己的事
 */
public class Coupon {
  private String couponId;
  private String userId;
  private boolean used;

  public Coupon(String couponId, String userId) {
    this.couponId = couponId;
  }

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

  public void use(String userId) {
    used = true;
    this.userId = userId;
  }
}
