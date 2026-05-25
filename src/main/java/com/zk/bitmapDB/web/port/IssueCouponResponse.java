package com.zk.bitmapDB.web.port;

import java.util.List;

public class IssueCouponResponse {

    private List<String> cpCodes;
    private List<Integer> bitmapValues;
    private String issueCustNo;
    private String message;

    public IssueCouponResponse(List<String> cpCodes, List<Integer> bitmapValues, String issueCustNo, String message) {
        this.cpCodes = cpCodes;
        this.bitmapValues = bitmapValues;
        this.issueCustNo = issueCustNo;
        this.message = message;
    }

    public List<String> getCpCodes() { return cpCodes; }
    public void setCpCodes(List<String> cpCodes) { this.cpCodes = cpCodes; }

    public List<Integer> getBitmapValues() { return bitmapValues; }
    public void setBitmapValues(List<Integer> bitmapValues) { this.bitmapValues = bitmapValues; }

    public String getIssueCustNo() { return issueCustNo; }
    public void setIssueCustNo(String issueCustNo) { this.issueCustNo = issueCustNo; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}