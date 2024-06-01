package com.back.travelit.dto.response.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int PRO_ID; //상품 아이디
    private int USER_ID; //유저 아이디
    private String PRO_NAME; //상품명
    private String PRO_CATEGORY_TOTAL; //상품 대분류 ex)레저 등
    private String PRO_CATEGORY_DETAIL; //상품 소분류 ex)수상스키 등
    private String PRO_CONTENT; //상품 설명
    private String PRO_RES; //예약 또는 구매 가능 여부
    private int PRO_PRICE; //상품 가격
    private int PRO_PER_MAX; //상품 구매가능 제한 수
    private Date PRO_DATE; //상품 등록일
    private String PRO_LOCATION; //상품 지역
    private int PRO_VIEW; //조회수
    private int TOUR_DATE; //이용 일 수
    private int TOUR_TIME; //이용 시간
    private Date TOUR_START; //시작일
    private Date TOUR_END; //종료일
    private String PRO_IMG_NAME;
    private String PRO_DEIMG_NAME;

    @Override
    public String toString() {
        return "ProductResponse{" +
                "PRO_ID=" + PRO_ID +
                ", USER_ID=" + USER_ID +
                ", PRO_NAME='" + PRO_NAME + '\'' +
                ", PRO_CATEGORY_TOTAL='" + PRO_CATEGORY_TOTAL + '\'' +
                ", PRO_CATEGORY_DETAIL='" + PRO_CATEGORY_DETAIL + '\'' +
                ", PRO_CONTENT='" + PRO_CONTENT + '\'' +
                ", PRO_RES='" + PRO_RES + '\'' +
                ", PRO_PRICE=" + PRO_PRICE +
                ", PRO_PER_MAX=" + PRO_PER_MAX +
                ", PRO_DATE=" + PRO_DATE +
                ", PRO_LOCATION='" + PRO_LOCATION + '\'' +
                ", PRO_VIEW=" + PRO_VIEW +
                ", TOUR_DATE=" + TOUR_DATE +
                ", TOUR_TIME=" + TOUR_TIME +
                ", TOUR_START=" + TOUR_START +
                ", TOUR_END=" + TOUR_END +
                ", PRO_IMG_NAME='" + PRO_IMG_NAME +
                ", PRO_DEIMG_NAME='" + PRO_DEIMG_NAME +
                '}';
    }
}
