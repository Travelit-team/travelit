package com.back.travelit.controller.product;

import com.back.travelit.common.s3.S3Service;
import com.back.travelit.dto.request.product.Message;
import com.back.travelit.dto.request.product.ProductRequest;
import com.back.travelit.dto.request.product.ProductSearch;
import com.back.travelit.dto.response.product.ProductPagingResponse;
import com.back.travelit.dto.response.product.ProductResponse;
import org.springframework.ui.Model;
import com.back.travelit.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final S3Service s3Service;

    //상품 목록 조회
    @GetMapping("/product/productList")
    public String productList(@ModelAttribute("params") final ProductSearch params, Model model) {
        ProductPagingResponse<ProductResponse> response = productService.findAllProduct(params);
        model.addAttribute("response", response);
        return "product/productList";
    }

    //상품 상세 조회
    @GetMapping("/product/productView")
    public String productView(@RequestParam final int PRO_ID, Model model) {
        ProductResponse product = productService.findProductById(PRO_ID);
        model.addAttribute("product", product);
        return "product/productView";
    }

    //상품 작성 페이지
    @GetMapping("/product/productWrite")
    public String productWrite(@RequestParam(value = "PRO_ID", required = false) final Integer PRO_ID, Model model) {
        if(PRO_ID != null) {
            ProductResponse product = productService.findProductById(PRO_ID);
            model.addAttribute("product", product);
        }
        return "product/productWrite";
    }

    //상픔 작성
    @PostMapping("/product/save")
    public String saveProduct(final ProductRequest params, Model model) {
        int PRO_ID = productService.saveProduct(params);
        Message message = new Message("게시글 생성이 완료되었습니다.", "/product/productList", RequestMethod.GET, null);
        return AlertMessage(message, model);
    }

    //상품 수정
    @PostMapping("/product/update")
    public String updateProduct(final ProductRequest params, Model model) {
        productService.updateProduct(params);
        Message message = new Message("게시글 수정이 완료되었습니다.", "/product/productList", RequestMethod.GET, null);
        return AlertMessage(message, model);
    }

    //게시글 삭제
    @PostMapping("/product/delete")
    public String deleteProduct(@RequestParam final Integer PRO_ID, Model model) {
        productService.deleteProduct(PRO_ID);
        Message message = new Message("게시글 삭제가 완료되었습니다.", "/product/productList", RequestMethod.GET, null);
        return AlertMessage(message, model);
    }

    //알림창
    private String AlertMessage(final Message params, Model model) {
        model.addAttribute("params", params);
        return "product/alertMessage";
    }
}
