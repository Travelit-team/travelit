package com.back.travelit.dto.response.product;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductReviewResponse {
    private int PRO_REVIEW_ID;
    private int USER_ID;
    private String NICKNAME;
    private int PRO_ID;
    private String PRO_REVIEW_CONTENT;
    private LocalDateTime PRO_REVIEW_DATE;
    private int PRO_SCORE;
}
