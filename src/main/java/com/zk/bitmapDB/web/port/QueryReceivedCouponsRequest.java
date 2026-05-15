package com.zk.bitmapDB.web.port;
import jakarta.validation.constraints.NotBlank;
public class QueryReceivedCouponsRequest {
    @NotBlank(message = "custNo is required")
    private String custNo;
    public String getCustNo() {
        return custNo;
    }
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
}