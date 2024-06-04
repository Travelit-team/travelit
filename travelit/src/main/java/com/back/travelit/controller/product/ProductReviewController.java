package com.back.travelit.controller.product;

import com.back.travelit.dto.request.product.ProductReviewRequest;
import com.back.travelit.dto.response.product.ProductReviewResponse;
import com.back.travelit.security.LoginUser;
import com.back.travelit.security.dto.UserDTO;
import com.back.travelit.service.product.ProductReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/product/{PRO_ID}/reviews")
    public ProductReviewResponse saveProductReview(@LoginUser UserDTO user, @PathVariable final int PRO_ID, @RequestBody ProductReviewRequest params) {
        int userId = user.getUserId();
        params.setUserId(userId);
        int PRO_REVIEW_ID = productReviewService.saveReview(params);
        return productReviewService.findReviewById(PRO_REVIEW_ID);
    }

    //리뷰 수정
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PatchMapping("/product/{PRO_ID}/reviews/{pro_REVIEW_ID}")
    public ProductReviewResponse updateReview(@PathVariable final int PRO_ID, @PathVariable final int pro_REVIEW_ID, @RequestBody final ProductReviewRequest params) {
        productReviewService.updateReview(params);
        return productReviewService.findReviewById(pro_REVIEW_ID);
    }

    //리뷰 삭제
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/product/{PRO_ID}/reviews/{pro_REVIEW_ID}")
    public int deleteReview(@PathVariable final int PRO_ID, @PathVariable final int pro_REVIEW_ID) {
        return productReviewService.deleteReview(pro_REVIEW_ID);
    }



}
