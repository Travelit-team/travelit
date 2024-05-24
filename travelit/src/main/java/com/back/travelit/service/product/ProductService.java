package com.back.travelit.service.product;

import com.back.travelit.dto.request.product.ProductRequest;
import com.back.travelit.dto.request.product.ProductSearch;
import com.back.travelit.dto.response.product.ProductPagination;
import com.back.travelit.dto.response.product.ProductPagingResponse;
import com.back.travelit.dto.response.product.ProductResponse;
import com.back.travelit.mapper.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    //상품 목록 조회
    public ProductPagingResponse<ProductResponse> findAllProduct(ProductSearch params){
        //해당 데이터가 없을 때, null을 담아 반환
        int count = productMapper.count(params);
        if (count < 1) {
            return new ProductPagingResponse<>(Collections.emptyList(), null);
        }

        //페이지 계산 후 계산된 페이지 정보 저장
        ProductPagination pagination = new ProductPagination(count, params);
        params.setProductPagination(pagination);

        //계산된 페이지 일부 조회 데이터 반환
        List<ProductResponse> list = productMapper.findAll(params);
        return new ProductPagingResponse<>(list, pagination);
    }

    //상품 상세 조회
    public ProductResponse findProductById(final int PRO_ID) {
        return productMapper.findById(PRO_ID);
    }

    //상품 입력
    @Transactional
    public int saveProduct(final ProductRequest params) {
        productMapper.save(params);
        return params.getPRO_ID();
    }

    //상품 수정
    @Transactional
    public int updateProduct(final ProductRequest params) {
        productMapper.update(params);
        return params.getPRO_ID();
    }

    //상품 삭제
    public int deleteProduct(final int PRO_ID) {
        productMapper.deleteById(PRO_ID);
        return PRO_ID;
    }

}
