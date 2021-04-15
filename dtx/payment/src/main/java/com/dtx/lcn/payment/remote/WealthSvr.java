package com.dtx.lcn.payment.remote;

import com.dtx.lcn.core.api.WealthApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "wealth")
public interface WealthSvr extends WealthApi {
}
