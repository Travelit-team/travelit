package com.back.travelit.mapper.product;

import com.back.travelit.dto.request.product.ReservationRequest;
import com.back.travelit.dto.response.product.ReservationResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {

    //전체 예약내역
    List<ReservationResponse> findAllRes();

    //상세 예약 내역
    ReservationResponse findByResId(int RES_ID);

    //예약 입력
    void saveRes(ReservationRequest params);

    //예약 삭제
    void deleteByResId(int RES_ID);

}
