package com.back.travelit.service.product;

import com.back.travelit.dto.request.product.ProductReviewRequest;
import com.back.travelit.dto.response.product.ProductReviewResponse;
import com.back.travelit.mapper.product.ProductReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

    private final ProductReviewMapper productReviewMapper;

    //리뷰 목록
    public List<ProductReviewResponse> findAllReview(final int PRO_ID){
        return productReviewMapper.findAll(PRO_ID);
    }

    //리뷰 상세 정보
    public ProductReviewResponse findReviewById(final int PRO_REVIEW_ID){
        return productReviewMapper.findById(PRO_REVIEW_ID);
    }

    //리뷰 저장
    @Transactional
    public int saveReview(final ProductReviewRequest params){
        productReviewMapper.save(params);
        return params.getProId();
    }

    //리뷰 수정
    @Transactional
    public int updateReview(final ProductReviewRequest params){
        productReviewMapper.update(params);
        return params.getProReviewId();
    }

    //리뷰 삭제
    @Transactional
    public int deleteReview(final int PRO_REVIEW_ID){
        productReviewMapper.deleteById(PRO_REVIEW_ID);
        return PRO_REVIEW_ID;
    }


}
