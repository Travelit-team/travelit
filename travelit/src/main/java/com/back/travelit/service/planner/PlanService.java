package com.back.travelit.service.planner;

import com.back.travelit.dto.request.planner.PlanCreateReqDTO;
import com.back.travelit.mapper.planner.PlanMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlanService {

    private final PlanMapper planMapper;

    public void setMakePlan(PlanCreateReqDTO createReqDTO){
        planMapper.insertMakePlan(createReqDTO);
        int planId = createReqDTO.getPlanId();
        setMakePlanLoc(createReqDTO.getState(),planId);
    }
    private void setMakePlanLoc(String str, int planId){
        List<String> strArr = Arrays.stream(str.split(",")).collect(Collectors.toList());
        planMapper.insertMakePlanLoc(strArr,planId);
    }
}
