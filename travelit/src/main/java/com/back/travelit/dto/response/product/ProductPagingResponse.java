package com.back.travelit.dto.response.product;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductPagingResponse<T> { //<T> 제너릭은 Type을 뜻하며, 어떤 타입의 객체라도 받는다.
    private List<T> list = new ArrayList<>();
    private ProductPagination productPagination;

    public ProductPagingResponse(List<T> list, ProductPagination productPagination) {
        this.list.addAll(list);
        this.productPagination = productPagination;
    }
}
