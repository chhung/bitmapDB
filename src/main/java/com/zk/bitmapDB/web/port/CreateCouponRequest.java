package com.zk.bitmapDB.web.port;

import jakarta.validation.constraints.NotBlank;

public class CreateCouponRequest {

    @NotBlank(message = "cpCode is required")
    private String cpCode;

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }
}

