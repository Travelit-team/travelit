package com.back.travelit.dto.request.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ScheduleDayRequest {

    @Setter
    private int schedId;
    private int plDay;

    public ScheduleDayRequest(int plDay) {
        this.plDay = plDay;
    }

}
