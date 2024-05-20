package com.back.travelit.controller;

import com.back.travelit.common.s3.S3Service;
import com.back.travelit.dto.test.request.S3DeleteRequest;
import com.back.travelit.dto.test.request.S3TestRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3TestController {

    private final S3Service s3Service;

    @PostMapping("/image/upload")
    public String s3Upload(@ModelAttribute S3TestRequest request) {
        if(request.getFiles().isEmpty()) {
            throw new RuntimeException("파일이 없습니다.");
        }

        List<String> imgUrls = s3Service.uploadFile(request.getFiles());

        System.out.println("imgUrls: " + imgUrls.toString());

        return imgUrls.toArray().toString();

    }

    @DeleteMapping("/image/delete")
    public String s3Delete(@ModelAttribute S3DeleteRequest request) {
        if(request.getDeleteFileNames().isEmpty()) {
            throw new RuntimeException("파일이 없습니다.");
        }

        s3Service.deleteFiles(request.getDeleteFileNames());

        return "success";
    }

}
