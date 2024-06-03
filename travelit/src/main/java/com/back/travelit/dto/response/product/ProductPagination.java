package com.back.travelit.dto.response.product;

import com.back.travelit.dto.request.product.ProductSearch;
import lombok.Getter;

@Getter
public class ProductPagination {
    private int totalRecordCount; //전체 데이터 수
    private int totalPageCount; //전체 페이지 수(페이지 하단)
    private int startPage; //시작 페이지
    private int endPage; //마지막 페이지
    private int limitStart;
    private boolean existPrevPage; // 이전 페이지
    private boolean existNextPage; //다음 페이지

    public ProductPagination(int totalRecordCount, ProductSearch params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            calculation(params);
        }
    }

    private void calculation(ProductSearch params) {

        //전체 수
        totalPageCount = ((totalRecordCount - 1) / params.getRecordSize()) + 1;

        //현재 페이지가 전체 페이지 수보다 클 때(전체 페이지는 페이지 하단에 뜨는 것만)
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        //시작 페이지
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        //마지막 페이지
        endPage = startPage + params.getPageSize() - 1;

        //마지막 페이지가 전체 페이지 수보다 클 때(전체 페이지는 페이지 하단에 뜨는 것만)
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        //LIMIT 시작
        limitStart = (params.getPage() - 1) * params.getRecordSize();

        //이전 페이지 있는지 확인
        existPrevPage = startPage != 1;

        // 다음 페이지 있는지 확인
        existNextPage = (endPage * params.getRecordSize()) < totalRecordCount;
    }
}
