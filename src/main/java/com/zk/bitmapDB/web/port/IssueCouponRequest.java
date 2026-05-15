package com.zk.bitmapDB.web.port;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class IssueCouponRequest {

    @NotBlank(message = "cpCode is required")
    private String cpCode;

    @NotEmpty(message = "issueCustNos is required")
    private List<String> issueCustNos;

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public List<String> getIssueCustNos() {
        return issueCustNos;
    }

    public void setIssueCustNos(List<String> issueCustNos) {
        this.issueCustNos = issueCustNos;
    }
}
