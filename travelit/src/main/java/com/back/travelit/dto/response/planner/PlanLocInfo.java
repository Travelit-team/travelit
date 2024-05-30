package com.back.travelit.dto.response.planner;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PlanLocInfo {
    //지역 정보 테이블 아이디
    private String locInfoId;
    //지역 코드
    private String locCode;
    //장소 이름
    private String locTitle;
    //장소 간단설명
    private String locSubTitle;
    //주소
    private String locAddress;
    //지역명
    private String locName;
}
