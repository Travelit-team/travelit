package com.back.travelit.dto.request.planner;

import lombok.Getter;

@Getter
public class ScheduleCreateReq {

    private int planId;

    private int planDay;

    private int locInfoId;
}
