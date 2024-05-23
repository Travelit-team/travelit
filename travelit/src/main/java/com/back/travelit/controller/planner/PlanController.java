package com.back.travelit.controller.planner;

import com.back.travelit.dto.request.planner.PlanCreateReqDTO;
import com.back.travelit.mapper.planner.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/planner")
public class PlanController {

    @Autowired
    private PlanMapper planMapper;

    //플래너 만들기 페이지
    @GetMapping("/plan-first")
    public String makePlanPage(){
        return "/planner/plan-first";
    }

    //플래너 만들기
    @PostMapping("/plan-make")
    public String makePlan(@ModelAttribute("createReqDTO)") PlanCreateReqDTO createReqDTO){
        planMapper.createPlan(createReqDTO);
        return "/planner/plan-second";
    }
}
