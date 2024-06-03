package com.back.travelit.dto.request.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFileRequest {
    private int PRO_IMG_ID; //이미지 번호
    private int PRO_ID; //상품 번호
    private String PRO_IMG_NAME; //이미지명
    private String PRO_IMG_SNAME; //이미지 사본명
    private int PRO_IMG_SIZE; //이미지 크기

    @Builder
    public ProductFileRequest(int PRO_ID, String PRO_IMG_NAME, String PRO_IMG_SNAME, int PRO_IMG_SIZE) {
        this.PRO_ID = PRO_ID;
        this.PRO_IMG_NAME = PRO_IMG_NAME;
        this.PRO_IMG_SNAME = PRO_IMG_SNAME;
        this.PRO_IMG_SIZE = PRO_IMG_SIZE;
    }

    public ProductFileRequest() {

    }

    public void setPRO_ID(int PRO_ID) {
        this.PRO_ID = PRO_ID;
    }


}

