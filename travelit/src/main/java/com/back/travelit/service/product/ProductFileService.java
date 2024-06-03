package com.back.travelit.service.product;

import com.back.travelit.dto.request.product.ProductFileRequest;
import com.back.travelit.mapper.product.ProductFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFileService {

    private final ProductFileMapper productFileMapper;

    @Transactional
    public void saveFile(final int PRO_ID, final List<ProductFileRequest> files) {
        System.out.println("PRO_ID S1"+PRO_ID);
        if (CollectionUtils.isEmpty(files)) {
            System.out.println("PRO_ID S2"+PRO_ID);
            return;
        }
        for (ProductFileRequest file : files) {
            file.setPRO_ID(PRO_ID);
            System.out.println("PRO_ID S3"+PRO_ID);
        }
        productFileMapper.saveAll(files);
    }
}

