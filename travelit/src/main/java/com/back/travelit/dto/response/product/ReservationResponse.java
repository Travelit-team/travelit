package com.back.travelit.dto.response.product;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ReservationResponse {
    private int RES_ID;
    private int USER_ID;
    private int PRO_ID;
    private String RES_NAME;
    private String RES_PHONE;
    private LocalDate RES_DATE;
    private LocalDate USE_DATE;
    private String RES_STATE;
    private int RES_NUM;
    private String RES_REQUEST;
    private String PAYMENT;
    private int PRO_PRICE;
    private String PRO_NAME;

    @Override
    public String toString() {
        return "ReservationResponse{" +
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
                ", PRO_PRICE=" + PRO_PRICE +
                '}';
    }

}
