package com.dtx.lcn.wealth.model;

import lombok.Data;

@Data
public class EventLog {
    private Integer id;

    private Integer eventId;

    private String bizIdentity;

    private String content;

    private Byte status;

    private Integer createdTime;
}