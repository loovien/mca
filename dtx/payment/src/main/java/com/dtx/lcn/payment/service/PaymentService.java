package com.dtx.lcn.payment.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dtx.lcn.core.dto.Result;
import com.dtx.lcn.payment.dao.TPaymentMapper;
import com.dtx.lcn.payment.model.TPayment;
import com.dtx.lcn.payment.remote.WealthSvr;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class PaymentService {

    private final WealthSvr wealthSvr;

    @Resource
    private TPaymentMapper tPaymentMapper;

    public PaymentService(WealthSvr wealthSvr) {
        this.wealthSvr = wealthSvr;
    }

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Transactional
    @LcnTransaction
    public int insert(TPayment record) {
        Result<?> result = wealthSvr.wealthAdd(100);
        tPaymentMapper.insert(record);
        log.info("===========================: {}: {}", record, result);
        return record.getId();
    }

    public int insertSelective(TPayment record) {
        return 0;
    }

    public TPayment selectByPrimaryKey(Integer id) {
        return null;
    }

    public int updateByPrimaryKeySelective(TPayment record) {
        return 0;
    }

    public int updateByPrimaryKey(TPayment record) {
        return 0;
    }
}
