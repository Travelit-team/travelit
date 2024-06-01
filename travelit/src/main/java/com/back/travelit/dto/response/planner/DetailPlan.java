package com.back.travelit.dto.response.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class DetailPlan {
    private String plTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createAt;
    private List<DetailPlanLoc> plLocNames;

}


