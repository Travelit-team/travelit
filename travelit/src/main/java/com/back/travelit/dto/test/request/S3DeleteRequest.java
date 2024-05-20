package com.back.travelit.dto.test.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class S3DeleteRequest {

    private List<String> deleteFileNames;
}
