package com.zk.bitmapDB.web;
import com.zk.bitmapDB.application.IssueCouponService;
import com.zk.bitmapDB.web.port.IssueCouponRequest;
import com.zk.bitmapDB.web.port.IssueCouponResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IssueCouponController {
    private final IssueCouponService service;
    public IssueCouponController(IssueCouponService service) {
        this.service = service;
    }
    @PostMapping("/coupons/issue")
    public IssueCouponResponse issue(@RequestBody @Valid IssueCouponRequest req) {
        List<Integer> bitmapValues = service.issue(req.getCpCodes(), req.getIssueCustNo());
        return new IssueCouponResponse(
                req.getCpCodes(),
                bitmapValues,
                req.getIssueCustNo(),
                "OK"
        );
    }
}