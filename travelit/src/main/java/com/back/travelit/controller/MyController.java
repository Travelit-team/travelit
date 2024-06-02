package com.back.travelit.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {



    @GetMapping("/my")
    @ResponseBody
    public String myAPI(HttpServletRequest request) {
        // 세션을 통해 현재 로그인한 사용자의 정보를 가져옵니다.
        HttpSession session = request.getSession();
        String loggedInUsername = (String) session.getAttribute("username");

        // 페이지에 표시할 HTML 코드
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<iframe src=\"https://giphy.com/embed/RfnQuPrBYOzpdh8yrh\" width=\"480\" height=\"480\" style=\"\" frameBorder=\"0\" class=\"giphy-embed\" allowFullScreen></iframe>");
        htmlBuilder.append("<p><a href=\"https://giphy.com/gifs/loop-psychedelic-sunset-RfnQuPrBYOzpdh8yrh\"></a></p>");
        htmlBuilder.append("<p>현재 로그인한 회원: ").append(loggedInUsername).append("</p>");

        return htmlBuilder.toString();
    }


    //restAPI연습
    @GetMapping("/api/hello")
    public String hello() {
        return "현재 서버시간은 "+new Date()   + "입니다. \n";
    }
}


