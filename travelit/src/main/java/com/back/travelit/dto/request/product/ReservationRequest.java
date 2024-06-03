package com.back.travelit.dto.request.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ReservationRequest {
    private int RES_ID;
    private int USER_ID;
    private int PRO_ID;
    private String RES_NAME;
    private String RES_PHONE;
    private Date RES_DATE;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date USE_DATE;
    private String RES_STATE;
    private int RES_NUM;
    private String RES_REQUEST;
    private String PAYMENT;
    private int RES_PRICE;

    public void setRES_ID(int RES_ID) {
        this.RES_ID = RES_ID;
    }
}
