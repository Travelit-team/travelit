package com.back.travelit.controller.planner;

import com.back.travelit.dto.request.planner.PlanCreateReqDTO;
import com.back.travelit.service.planner.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlanController {

    @Autowired
    private PlanService planService;

    //플래너 만들기 페이지
    @GetMapping("/plan-first")
    public String makePlanPage(){
        return "/planner/plan-first";
    }

    //플래너 만들기
    @PostMapping("/plan-make")
    public String makePlan(@ModelAttribute("createReqDTO)") PlanCreateReqDTO createReqDTO){
        planService.setMakePlan(createReqDTO);
        return "/planner/plan-second";
    }
}
