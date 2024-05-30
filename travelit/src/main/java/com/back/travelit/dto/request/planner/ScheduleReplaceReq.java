package com.back.travelit.dto.request.planner;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ScheduleReplaceReq {

    @Setter
    private int schedId;


    //플래너 아이디
    private int planId;
    //플래너 일차
    private int planDay;
    //장소 아이디
    private int locInfoId;

    public ScheduleReplaceReq(int planId, int planDay, int locInfoId) {
        this.planId = planId;
        this.planDay = planDay;
        this.locInfoId = locInfoId;
    }
}
