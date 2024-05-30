package com.back.travelit.controller.planner;

import com.back.travelit.dto.request.planner.PlanCreateReq;
import com.back.travelit.dto.request.planner.ScheduleCreateReq;
import com.back.travelit.dto.request.planner.ScheduleReplaceReq;
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
    public String makePlan(@ModelAttribute("createReqDTO") PlanCreateReq createReqDTO, Model model){
        
        //플래너 기본 정보 값 넣고,생성된 플래너 아이디 값 받기
        int planId = planService.setMakePlan(createReqDTO);

        log.info("planId");
        //user가 입력한 플래너 값을 리턴하는 view에 보내기
        model.addAttribute("createReqDTO", createReqDTO);
        
        //지역 코드를 이용하여 지역 정보 값 받기
        List<PlanLocCodeRes> states = planService.selectAllLocCode(createReqDTO.getLocCode());
        //지역 코드 및 이름 값을 리턴하는 view에 보내기
        model.addAttribute("states", states);

        return "/planner/plan-second";
    }

    //스케줄 생성
    @PostMapping("/make-sched")
    public String makeShed(@ModelAttribute("schedCreateReq") ScheduleCreateReq schedCreateReq, Model model){

        //상세 스케줄 값 넣기
        planService.setMakeSched(schedCreateReq);
        return "/planner/plan-detail";
    }

    //스케줄 수정
    @GetMapping("/edit-plan/{plan_id}")
    public String editPlan(@ModelAttribute("scheduleReplaceReq") ScheduleReplaceReq scheduleReplaceReq, Model model){
        planService.setReschedule(scheduleReplaceReq);
        return "/planner/plan-detail";
    }
}
