package com.zk.bitmapDB.web.port;
import java.util.List;
public class QueryReceivedCouponsResponse {
    private String custNo;
    private int totalCount;
    private List<String> coupons;
    public QueryReceivedCouponsResponse(String custNo, List<String> coupons) {
        this.custNo = custNo;
        this.totalCount = coupons.size();
        this.coupons = coupons;
    }
    public String getCustNo() { return custNo; }
    public void setCustNo(String custNo) { this.custNo = custNo; }
    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }
    public List<String> getCoupons() { return coupons; }
    public void setCoupons(List<String> coupons) { this.coupons = coupons; }
}