package com.back.travelit.dto.request.planner;

import lombok.Getter;

import java.util.List;

@Getter
public class ScheduleRequest {
    private int planId;
    private List<DaySchedule> schedule;

    @Getter
    public static class DaySchedule {
        private int day;
        private List<Integer> locationInfoIds;

    }
}
