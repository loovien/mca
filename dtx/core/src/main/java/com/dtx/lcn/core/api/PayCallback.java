package com.dtx.lcn.core.api;

import com.dtx.lcn.core.dto.PayCallbackDto;
import com.dtx.lcn.core.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface PayCallback {
    @GetMapping("/payment/callback")
    Result<?> callback(@RequestParam(name = "orderId") String orderId);
}
