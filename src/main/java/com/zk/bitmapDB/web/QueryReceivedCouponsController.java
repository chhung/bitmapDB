package com.zk.bitmapDB.web;
import com.zk.bitmapDB.application.QueryReceivedCouponsService;
import com.zk.bitmapDB.web.port.QueryReceivedCouponsRequest;
import com.zk.bitmapDB.web.port.QueryReceivedCouponsResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class QueryReceivedCouponsController {
    private final QueryReceivedCouponsService service;
    public QueryReceivedCouponsController(QueryReceivedCouponsService service) {
        this.service = service;
    }
    @PostMapping("/coupons/received")
    public QueryReceivedCouponsResponse query(@RequestBody @Valid QueryReceivedCouponsRequest req) {
        List<String> coupons = service.query(req.getCustNo());
        return new QueryReceivedCouponsResponse(req.getCustNo(), coupons);
    }
}