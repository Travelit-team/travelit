package com.back.travelit.dto.response.location;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationLikeResponse {
    //좋아요를 눌렀다면 -> true
    //좋아요를 취소했다면 -> false
    private boolean isLiked;
    private String message;
}
