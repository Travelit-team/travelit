package com.back.travelit.dto.response.location;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LocationPostResponse {

    private int locationInfoId;
    private String title;
    private String address;
    private String profileUrl;
    private int views;
    private int likeCount;
    private LocalDateTime createAt;

}
