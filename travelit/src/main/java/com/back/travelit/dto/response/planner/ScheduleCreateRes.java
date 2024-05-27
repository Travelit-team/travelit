package com.back.travelit.dto.response.planner;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScheduleCreateRes {

    //플래너 테이블 : PLAN
    //플래너 아이디
    private int planId;
    //플래너 제목
    private String planTitle;
    //출발일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDate;
    //마지막일
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate  endDate;
    //작성일
    private LocalDate createAt;

    //여행 일차
    private int planDay;


    //플래너 여행지 테이블 : PLAN_LOC

    //지역코드 테이블 : LOCATION_CODE

    //지역정보 테이블 : LOCATION_INFO


    //여행지
    private String locName;

    //지역 이름
    private String locTitle;
    //지역 부제목
    private String locSubTitle;

    //주소

}
