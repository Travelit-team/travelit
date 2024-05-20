package com.back.travelit.dto.test.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@Getter
public class S3TestRequest {

    private List<MultipartFile> files;
    private String title;
    private String contents;

}
