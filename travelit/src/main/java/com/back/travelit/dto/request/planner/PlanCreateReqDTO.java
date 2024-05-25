package com.back.travelit.dto.request.planner;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class PlanCreateReqDTO {

    @Setter
    private int planId;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate  endDate;

    private String state;

    public PlanCreateReqDTO(String title, LocalDate startDate, LocalDate endDate, String state) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }


}
