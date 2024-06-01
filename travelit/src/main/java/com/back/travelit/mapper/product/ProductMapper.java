package com.back.travelit.mapper.product;

import com.back.travelit.dto.request.product.ProductRequest;
import com.back.travelit.dto.request.product.ProductSearch;
import com.back.travelit.dto.response.product.ProductResponse;
import com.back.travelit.dto.response.product.ProductViewResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    //상품 목록 조회 //findAll() select 쿼리 호출
    List<ProductResponse> findAll(ProductSearch prams);

    //상품 상세 조회 //findById select 쿼리 호출
    ProductViewResponse findById(int PRO_ID);

    List<String> productImageUrl(int PRO_ID);

    List<String> productDeImageUrl(int PRO_ID);

    //게시글 입력 //save() insert 쿼리 호출
    void save(ProductRequest params);

    //게시글 수정 //update() update 쿼리 호출
    void update(ProductRequest params);

    //게시글 삭제 //deleteById update 쿼리 호출
    void deleteById(int PRO_ID);

    //전체 게시글 수
    int count(ProductSearch prams);

}
