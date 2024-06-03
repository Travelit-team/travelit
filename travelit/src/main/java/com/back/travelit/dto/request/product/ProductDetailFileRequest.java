package com.back.travelit.dto.request.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailFileRequest {
    private int PRO_DEIMG_ID; //이미지 번호
    private int PRO_ID; //상품 번호
    private String PRO_DEIMG_NAME; //이미지명
    private String PRO_DEIMG_SNAME; //이미지 사본명
    private int PRO_DEIMG_SIZE; //이미지 크기

    @Builder
    public ProductDetailFileRequest(int PRO_ID, String PRO_DEIMG_NAME, String PRO_DEIMG_SNAME, int PRO_DEIMG_SIZE) {
        this.PRO_ID = PRO_ID;
        this.PRO_DEIMG_NAME = PRO_DEIMG_NAME;
        this.PRO_DEIMG_SNAME = PRO_DEIMG_SNAME;
        this.PRO_DEIMG_SIZE = PRO_DEIMG_SIZE;
    }

    public ProductDetailFileRequest() {

    }

    public void setPRO_ID(int PRO_ID) {
        this.PRO_ID = PRO_ID;
    }


}

