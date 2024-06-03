package com.back.travelit.service.product;

import com.back.travelit.dto.request.product.ProductDetailFileRequest;
import com.back.travelit.mapper.product.ProductDetailFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailFileService {

    private final ProductDetailFileMapper productDetailFileMapper;

    @Transactional
    public void saveDetailFile(final int PRO_ID, final List<ProductDetailFileRequest> defiles) {
        System.out.println("PRO_ID S1"+PRO_ID);
        if (CollectionUtils.isEmpty(defiles)) {
            System.out.println("PRO_ID S2"+PRO_ID);
            return;
        }
        for (ProductDetailFileRequest defile : defiles) {
            defile.setPRO_ID(PRO_ID);
            System.out.println("PRO_ID S3"+PRO_ID);
        }
        productDetailFileMapper.savDetailAll(defiles);
    }
}

