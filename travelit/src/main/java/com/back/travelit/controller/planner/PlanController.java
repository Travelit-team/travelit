package com.back.travelit.controller.planner;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlanController {

    @GetMapping("plan-first")
    public String planFirst(){
       return "/planner/plan-first";
    }
}
