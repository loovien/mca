package com.dtx.lcn.wealth.dao;


import com.dtx.lcn.wealth.model.EventLog;

import java.util.List;

public interface EventLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EventLog record);

    int insertSelective(EventLog record);

    EventLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EventLog record);

    int updateByPrimaryKey(EventLog record);

    List<EventLog> selectRecentlyEventLog();
}