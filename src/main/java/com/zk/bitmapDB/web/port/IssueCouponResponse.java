package com.zk.bitmapDB.web.port;

import java.util.List;

public class IssueCouponResponse {

    private String cpCode;
    private int bitmapValue;
    private int totalIssued;
    private List<String> issuedCustNos;
    private String message;

    public IssueCouponResponse(String cpCode, int bitmapValue, List<String> issuedCustNos, String message) {
        this.cpCode = cpCode;
        this.bitmapValue = bitmapValue;
        this.totalIssued = issuedCustNos.size();
        this.issuedCustNos = issuedCustNos;
        this.message = message;
    }

    public String getCpCode() { return cpCode; }
    public void setCpCode(String cpCode) { this.cpCode = cpCode; }

    public int getBitmapValue() { return bitmapValue; }
    public void setBitmapValue(int bitmapValue) { this.bitmapValue = bitmapValue; }

    public int getTotalIssued() { return totalIssued; }
    public void setTotalIssued(int totalIssued) { this.totalIssued = totalIssued; }

    public List<String> getIssuedCustNos() { return issuedCustNos; }
    public void setIssuedCustNos(List<String> issuedCustNos) { this.issuedCustNos = issuedCustNos; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}