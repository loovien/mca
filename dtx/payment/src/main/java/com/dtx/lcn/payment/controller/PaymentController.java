package com.dtx.lcn.payment.controller;

import com.dtx.lcn.core.api.PayCallback;
import com.dtx.lcn.core.dto.Result;
import com.dtx.lcn.payment.model.TPayment;
import com.dtx.lcn.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController implements PayCallback {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @Override
    @GetMapping("/callback")
    public Result<?> callback(@RequestParam(name = "orderId", defaultValue = "system-test") String orderId) {
        if (orderId == null || orderId.length() <= 0) {
            return Result.wrapOK("order_id invalid!");
        }
        TPayment tPayment = new TPayment();
        tPayment.setOrderId(orderId);
        paymentService.insert(tPayment);
        return Result.wrapOK(null);
    }
}
