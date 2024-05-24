package com.back.travelit.mapper.planner;

import com.back.travelit.dto.request.planner.PlanCreateReqDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanMapper {
    void insertMakePlan(PlanCreateReqDTO createReqDTO);

    void insertMakePlanLoc(@Param("strArr") List<String> strArr, @Param("planId") int planId);
}
