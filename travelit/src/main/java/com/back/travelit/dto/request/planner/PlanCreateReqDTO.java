package com.back.travelit.dto.request.planner;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanCreateReqDTO {

    private int userId;

    private String title;
    private LocalDateTime startDate;
    private LocalDateTime  endDate;
    private String state;
}
