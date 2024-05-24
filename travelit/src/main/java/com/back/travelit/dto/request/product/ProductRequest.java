package com.back.travelit.dto.request.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
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
    private LocalDate TOUR_START; //시작일
    private LocalDate TOUR_END; //종료일
    private List<MultipartFile> files = new ArrayList<>(); //첨부파일
}
