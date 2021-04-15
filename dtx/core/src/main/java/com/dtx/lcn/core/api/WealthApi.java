package com.dtx.lcn.core.api;

import com.dtx.lcn.core.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface WealthApi {

    @GetMapping("/wealth/rmb")
    Result<?> wealthAdd(@RequestParam(name = "rmb") Integer money);
}
