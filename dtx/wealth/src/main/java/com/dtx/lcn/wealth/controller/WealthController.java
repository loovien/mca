package com.dtx.lcn.wealth.controller;

import com.dtx.lcn.core.api.WealthApi;
import com.dtx.lcn.core.dto.Result;
import com.dtx.lcn.wealth.model.TWealth;
import com.dtx.lcn.wealth.service.TWealthService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WealthController implements WealthApi {

    private final TWealthService tWealthMapper;

    public WealthController(TWealthService tWealthMapper) {
        this.tWealthMapper = tWealthMapper;
    }


    @Override
    public Result<?> wealthAdd(Integer money) {
        TWealth tWealth = new TWealth();
        tWealth.setWealth(money);
        try {
            tWealthMapper.insert(tWealth);
            return Result.wrapOK(null);
        } catch (Exception exception) {
            return Result.wrapOK("xxx");
        }
    }
}
