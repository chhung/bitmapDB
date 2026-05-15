package com.zk.bitmapDB.web;
import com.zk.bitmapDB.application.IssueCouponService;
import com.zk.bitmapDB.web.port.IssueCouponRequest;
import com.zk.bitmapDB.web.port.IssueCouponResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueCouponController {
    private final IssueCouponService service;
    public IssueCouponController(IssueCouponService service) {
        this.service = service;
    }
    @PostMapping("/coupons/issue")
    public IssueCouponResponse issue(@RequestBody @Valid IssueCouponRequest req) {
        int bitmapValue = service.issue(req.getCpCode(), req.getIssueCustNos());
        return new IssueCouponResponse(
                req.getCpCode(),
                bitmapValue,
                req.getIssueCustNos(),
                "OK"
        );
    }
}