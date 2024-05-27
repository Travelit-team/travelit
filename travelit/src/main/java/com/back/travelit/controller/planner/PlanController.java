package com.back.travelit.controller.planner;

import com.back.travelit.dto.request.planner.PlanCreateReq;
import com.back.travelit.dto.response.planner.PlanLocCodeRes;
import com.back.travelit.service.planner.PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/planner")
@RequiredArgsConstructor
public class PlanController {

    @Autowired
    private PlanService planService;
    private Model model;
    private ModelAndView modelAndView;

    //플래너 만들기 페이지
    @GetMapping("/plan-first")
    public String makePlanPage(){
        return "/planner/plan-first";
    }

    //플래너 만들기
    @PostMapping("/plan-make")
    public String makePlan(@ModelAttribute("createReqDTO)") PlanCreateReq createReqDTO, Model model){
        int planId = planService.setMakePlan(createReqDTO);
        log.info("planId");
        model.addAttribute("createReqDTO", createReqDTO);
        List<PlanLocCodeRes> states = planService.selectAllLocCode(createReqDTO.getStateCode());
        model.addAttribute("states", states);
        return "/planner/plan-second";
    }

   /* //스케줄 생성
    @PostMapping("/plan-shed")
    public String makeShed(){
        return "/planner/plan-detail";
    }*/
}
