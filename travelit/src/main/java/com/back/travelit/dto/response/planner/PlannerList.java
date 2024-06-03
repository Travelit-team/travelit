package com.back.travelit.dto.response.planner;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PlannerList {
    private int planId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String plTitle;
    private LocalDate createAt;
    private String locations;
}
