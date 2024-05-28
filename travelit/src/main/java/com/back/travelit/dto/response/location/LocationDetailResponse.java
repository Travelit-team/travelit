package com.back.travelit.dto.response.location;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LocationDetailResponse {
    private int locationInfoId;
    private String title;
    private String subTitle;
    private String description;
    private String address;
    private double latitude; //위도
    private double longitude; //경도
    private int views;
    private int likeCount;
    private int bookmarkCount;
    private LocalDateTime createAt;
}
