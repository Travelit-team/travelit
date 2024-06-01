package com.back.travelit.controller.product;

import com.back.travelit.dto.request.product.ProductReviewRequest;
import com.back.travelit.dto.response.product.ProductReviewResponse;
import com.back.travelit.service.product.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductReviewController {

    private final ProductReviewService productReviewService;


    //리뷰 리스트 조회
    @GetMapping("/product/{PRO_ID}/reviews")
    public List<ProductReviewResponse> findAllReview(@PathVariable final int PRO_ID) {
        return productReviewService.findAllReview(PRO_ID);
    }

    //리뷰 상세정보 조회
    @GetMapping("/product/{PRO_ID}/reviews/{PRO_REVIEW_ID}")
    public ProductReviewResponse findReviewById(@PathVariable final int PRO_ID, @PathVariable final int PRO_REVIEW_ID) {
        return productReviewService.findReviewById(PRO_REVIEW_ID);
    }

    //새 리뷰 작성
    //REST API에서는 명사, 소문자 /는 계층을 나태내므로 마지마겡 들어가지 않도록 주의
    @PostMapping("/product/{PRO_ID}/reviews")
    public ProductReviewResponse saveProductReview(@PathVariable final int PRO_ID, @RequestBody ProductReviewRequest params) {
        int PRO_REVIEW_ID = productReviewService.saveReview(params);
        System.out.println("params: " + params);
        return productReviewService.findReviewById(PRO_REVIEW_ID);
    }

    //리뷰 수정
    @PatchMapping("/product/{PRO_ID}/reviews/{pro_REVIEW_ID}")
    public ProductReviewResponse updateReview(@PathVariable final int PRO_ID, @PathVariable final int pro_REVIEW_ID, @RequestBody final ProductReviewRequest params) {
        productReviewService.updateReview(params);
        return productReviewService.findReviewById(pro_REVIEW_ID);
    }

    // 댓글 삭제
    @DeleteMapping("/product/{PRO_ID}/reviews/{pro_REVIEW_ID}")
    public int deleteReview(@PathVariable final int PRO_ID, @PathVariable final int pro_REVIEW_ID) {
        return productReviewService.deleteReview(pro_REVIEW_ID);
    }



}
