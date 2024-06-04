package com.back.travelit.service.product;

import com.back.travelit.dto.request.product.ReservationRequest;
import com.back.travelit.dto.response.product.ReservationResponse;
import com.back.travelit.mapper.product.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationMapper reservationMapper;

    //예약목록 조회
    public List<ReservationResponse> findAllRes(int USER_ID) {
        return  reservationMapper.findAllRes(USER_ID);
    }

    //예약 상세 조회
    public ReservationResponse findByResId(final int RES_ID) {
        return  reservationMapper.findByResId(RES_ID);
    }

    //예약 입력
    @Transactional
    public int saveRes(final ReservationRequest params){
        reservationMapper.saveRes(params);
        return  params.getRES_ID();
    }

    //예약 삭제
    public int deleteRes(final int RES_ID){
        reservationMapper.deleteByResId(RES_ID);
        return RES_ID;
    }

    //조회순 5개 상품 조회
    public List<ReservationResponse> findByRand() {
        return reservationMapper.findByRand();
    }
}
