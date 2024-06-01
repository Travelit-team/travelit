package com.back.travelit.controller.product;

import com.back.travelit.common.s3.S3Service;
import com.back.travelit.dto.request.product.*;
import com.back.travelit.dto.response.product.ProductPagingResponse;
import com.back.travelit.dto.response.product.ProductResponse;
import com.back.travelit.dto.response.product.ProductViewResponse;
import com.back.travelit.service.product.ProductDetailFileService;
import com.back.travelit.service.product.ProductFileService;
import com.back.travelit.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductFileService productFileService;
    private final ProductDetailFileService productDetailFileService;
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
        ProductViewResponse product = productService.findProductById(PRO_ID);
        List<String> productImageUrl = productService.productImageUrl(PRO_ID);
        List<String> productDeImageUrl = productService.productDeImageUrl(PRO_ID);
        model.addAttribute("product", product);
        model.addAttribute("productImageUrl", productImageUrl);
        model.addAttribute("productDeImageUrl", productDeImageUrl);
        System.out.println("productImageUrl"+productImageUrl);
        System.out.println("productDeImageUrl"+productDeImageUrl);
        return "product/productView";
    }

    //상품 작성 페이지
    @GetMapping("/product/productWrite")
    public String productWrite(@RequestParam(value = "PRO_ID", required = false) final Integer PRO_ID, Model model) {
        if(PRO_ID != null) {
            ProductViewResponse product = productService.findProductById(PRO_ID);
            model.addAttribute("product", product);
        }
        return "product/productWrite";
    }

    //상픔 작성
    @PostMapping("/product/save")
    public String saveProduct(final ProductRequest params, Model model, @RequestParam("files") List<MultipartFile> files, @RequestParam("defiles") List<MultipartFile> defiles) {
        //1.게시글 삽입
        int PRO_ID = productService.saveProduct(params);
        //2.파일 업로드
        //MultipartFile을 S3에 업로드하고 URL을 반환받음
        List<String> fileUrls = s3Service.uploadFile(files);
        List<String> fileDeUrls = s3Service.uploadFile(defiles);

        //3.업로드된 파일의 URL을 ProductFileRequest로 변환하여 DB에 저장
        List<ProductFileRequest> productFileRequests = new ArrayList<>();
        for (String fileUrl : fileUrls) {
            ProductFileRequest fileRequest = new ProductFileRequest();
            fileRequest.setPRO_ID(PRO_ID);
            //업로드된 파일의 URL을 DB에 저장
            fileRequest.setPRO_IMG_NAME(fileUrl);
            fileRequest.setPRO_IMG_SNAME(fileUrl);
            //파일 크기 등의 정보는 생략
            productFileRequests.add(fileRequest);
        }
        List<ProductDetailFileRequest> productDetailFileRequests = new ArrayList<>();
        for (String fileDeUrl : fileDeUrls) {
            ProductDetailFileRequest defileRequest = new ProductDetailFileRequest();
            defileRequest.setPRO_ID(PRO_ID);
            //업로드된 파일의 URL을 DB에 저장
            defileRequest.setPRO_DEIMG_NAME(fileDeUrl);
            defileRequest.setPRO_DEIMG_SNAME(fileDeUrl);
            //파일 크기 등의 정보는 생략
            productDetailFileRequests.add(defileRequest);
        }

        //파일정보 DB 저장
        productFileService.saveFile(PRO_ID, productFileRequests);
        productDetailFileService.saveDetailFile(PRO_ID, productDetailFileRequests);
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
    public String deleteProduct(@RequestParam final Integer PRO_ID, final ProductSearch queryParams, Model model) {
        productService.deleteProduct(PRO_ID);
        Message message = new Message("게시글 삭제가 완료되었습니다.", "/product/productList", RequestMethod.GET, queryParamsToMap(queryParams));
        return AlertMessage(message, model);
    }

    //알림창
    private String AlertMessage(final Message params, Model model) {
        model.addAttribute("params", params);
        return "product/alertMessage";
    }

    //productDelete()dml 이전 페이지 정보 저장
    private Map<String, Object> queryParamsToMap(final ProductSearch queryParams) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", queryParams.getPage());
        data.put("recordSize", queryParams.getRecordSize());
        data.put("pageSize", queryParams.getPageSize());
        data.put("keyword", queryParams.getKeyword());
        data.put("searchType", queryParams.getSearchType());
        return data;
    }



}

