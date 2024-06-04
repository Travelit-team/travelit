package com.back.travelit.dto.request.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductReviewRequest {
    private int proReviewId;
    private int userId;
    private int proId;
    private String proReviewContent;
    private LocalDateTime proReviewDate;
    private int proScore;
    private int resId;

}
