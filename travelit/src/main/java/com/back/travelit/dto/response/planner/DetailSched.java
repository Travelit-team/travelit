package com.back.travelit.dto.response.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DetailSched {
    private String plDay;
    private List<DetailSchedInfo> schedInfos;
}
