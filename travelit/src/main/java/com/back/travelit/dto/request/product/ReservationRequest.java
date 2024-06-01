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

    @Override
    public String toString() {
        return "ReservationRequest{" +
                "RES_ID=" + RES_ID +
                ", USER_ID=" + USER_ID +
                ", PRO_ID=" + PRO_ID +
                ", RES_NAME='" + RES_NAME +
                ", RES_PHONE='" + RES_PHONE +
                ", RES_DATE=" + RES_DATE +
                ", USE_DATE=" + USE_DATE +
                ", RES_STATE='" + RES_STATE +
                ", RES_NUM=" + RES_NUM +
                ", RES_REQUEST='" + RES_REQUEST +
                ", PAYMENT='" + PAYMENT +
                ", RES_PRICE=" + RES_PRICE +
                '}';
    }

}
