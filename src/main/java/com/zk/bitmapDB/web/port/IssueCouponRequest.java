package com.zk.bitmapDB.web.port;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class IssueCouponRequest {

    @NotEmpty(message = "cpCodes is required")
    private List<String> cpCodes;

    @NotBlank(message = "issueCustNo is required")
    private String issueCustNo;

    public List<String> getCpCodes() {
        return cpCodes;
    }

    public void setCpCodes(List<String> cpCodes) {
        this.cpCodes = cpCodes;
    }

    public String getIssueCustNo() {
        return issueCustNo;
    }

    public void setIssueCustNo(String issueCustNo) {
        this.issueCustNo = issueCustNo;
    }
}
