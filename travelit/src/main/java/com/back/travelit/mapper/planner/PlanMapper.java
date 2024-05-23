package com.back.travelit.mapper.planner;

import com.back.travelit.dto.request.planner.PlanCreateReqDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper {
    void createPlan(PlanCreateReqDTO createReqDTO);
}
