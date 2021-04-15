package com.dtx.lcn.payment.dao;

import com.dtx.lcn.payment.model.TPayment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TPaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPayment record);

    int insertSelective(TPayment record);

    TPayment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPayment record);

    int updateByPrimaryKey(TPayment record);
}