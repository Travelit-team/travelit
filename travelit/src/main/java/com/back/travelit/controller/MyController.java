package com.back.travelit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @GetMapping("/my")
    @ResponseBody
    public String myAPI() {

        return "희진희진희진희진";
    }
}

