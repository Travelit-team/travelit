package com.back.travelit.mapper.product;

import com.back.travelit.dto.request.product.ProductReviewRequest;
import com.back.travelit.dto.response.product.ProductReviewResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductReviewMapper {
    //리뷰 목록
    List<ProductReviewResponse> findAll(int PRO_ID);

    //리뷰 상세정보
    ProductReviewResponse findById(int PRO_REVIEW_ID);

    //리뷰 작성
    void save(ProductReviewRequest  params);

    //리뷰 수정
    void update(ProductReviewRequest  params);

    //리뷰 삭제
    void deleteById(int PRO_REVIEW_ID);

    //리뷰수 카운트
    int count(int PRO_ID);
}
