package com.dtx.lcn.wealth.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.dtx.lcn.wealth.dao.TWealthMapper;
import com.dtx.lcn.wealth.model.TWealth;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TWealthService {

    private final TWealthMapper tWealthMapper;

    public TWealthService(TWealthMapper tWealthMapper) {
        this.tWealthMapper = tWealthMapper;
    }

    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Transactional
    @LcnTransaction
    public int insert(TWealth record) {
        return tWealthMapper.insert(record);
    }

    public int insertSelective(TWealth record) {
        return 0;
    }

    public TWealth selectByPrimaryKey(Integer id) {
        return null;
    }

    public int updateByPrimaryKeySelective(TWealth record) {
        return 0;
    }

    public int updateByPrimaryKey(TWealth record) {
        return 0;
    }
}
