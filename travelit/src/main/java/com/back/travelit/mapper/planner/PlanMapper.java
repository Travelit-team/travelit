package com.back.travelit.mapper.planner;

import com.back.travelit.dto.request.planner.PlanCreateReq;
import com.back.travelit.dto.response.planner.PlanLocCodeRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlanMapper {
    void insertMakePlan(PlanCreateReq createReqDTO);

    void insertMakePlanLoc(@Param("strArr") List<String> strArr, @Param("planId") int planId);

    List<PlanLocCodeRes> selectAllLocCode(@Param("locCodes") List<String> strArr);
}
