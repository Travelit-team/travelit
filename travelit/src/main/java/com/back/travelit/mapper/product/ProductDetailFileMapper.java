package com.back.travelit.mapper.product;

import com.back.travelit.dto.request.product.ProductDetailFileRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDetailFileMapper {
    void savDetailAll(List<ProductDetailFileRequest> productDetailFiles);
}
