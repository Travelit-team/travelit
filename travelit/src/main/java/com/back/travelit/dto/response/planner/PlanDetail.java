package com.back.travelit.dto.response.planner;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Getter
public class PlanDetail {
    private String nickname;
    private String plTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createAt;
    private String locName;
    private int plDay;
    private int locId;
    private String locTitle;
    private String locSubTitle;
    private String address;

    private List<Map<String,Object>> planDetailInfo;
}
