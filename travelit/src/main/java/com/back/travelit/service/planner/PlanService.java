package com.back.travelit.service.planner;

import com.back.travelit.dto.request.planner.PlanCreateReq;
import com.back.travelit.dto.request.planner.ScheduleCreateReq;
import com.back.travelit.dto.response.planner.PlanLocCodeRes;
import com.back.travelit.mapper.planner.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanService {

    private final PlanMapper planMapper;

    public int setMakePlan(PlanCreateReq createReqDTO){
        planMapper.insertMakePlan(createReqDTO);
        int planId = createReqDTO.getPlanId();
        setMakePlanLoc(createReqDTO.getStateCode(), planId);

        return planId;
    }
    private void setMakePlanLoc(String str, int planId){
        List<String> strArr = Arrays.stream(str.split(",")).collect(Collectors.toList());
        planMapper.insertMakePlanLoc(strArr,planId);
    }

    public List<PlanLocCodeRes> selectAllLocCode(String str){
        List<String> strArr = Arrays.stream(str.split(",")).collect(Collectors.toList());
        return planMapper.selectAllLocCode(strArr);
    };
    public void setMakeShed (ScheduleCreateReq shedCreateReqDTO){

    }
}
