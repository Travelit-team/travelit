package com.back.travelit.dto.request.product;

import com.back.travelit.dto.response.product.ProductPagination;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSearch{
    private int page; //현재 페이지 번호
    private int recordSize; //페이지당 출력할 상품 개수
    private int pageSize; //화면 하단에 출력할 페이지 수
    private String keyword; //검색 키워드
    private String searchType; //검색 유형
    private ProductPagination productPagination;

    public ProductSearch() {
        this.page = 1;
        this.recordSize = 20;
        this.pageSize = 5;
    }

}
