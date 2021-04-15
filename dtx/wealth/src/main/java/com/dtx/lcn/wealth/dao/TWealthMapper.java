package com.dtx.lcn.wealth.dao;

import com.dtx.lcn.wealth.model.TWealth;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TWealthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TWealth record);

    int insertSelective(TWealth record);

    TWealth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TWealth record);

    int updateByPrimaryKey(TWealth record);
}