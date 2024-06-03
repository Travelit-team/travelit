package com.back.travelit.mapper.product;

import com.back.travelit.dto.request.product.ProductFileRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ProductFileMapper {
    void saveAll(List<ProductFileRequest> productFiles);
}
