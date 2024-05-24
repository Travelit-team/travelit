package com.back.travelit.dto.request.location;

import com.back.travelit.common.annotation.ValidImageFile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class LocationWriteRequest {

    private int locationInfoId;
    @ValidImageFile
    private List< MultipartFile> files;

    @NotBlank(message = "지역은 필수입니다.")
    private String locationCode;
    
    @Size(min = 2, max = 15, message = "제목의 길이는 2~15 글자 사이어야 합니다.")
    private String title;

    @Size(min = 2, max = 30, message = "부제목의 길이는 2~30 글자 사이어야 합니다.")
    private String subTitle;

    @Size(min = 1, max =10000, message = "상세 내용은 1 ~ 10000 글자 사이어야 합니다.")
    private String description;

    @NotBlank(message = "주소값은 필수입니다.")
    private String address;

    @DecimalMin(value = "-90.0", message = "위도는 -90.0 이상이어야 합니다.")
    @DecimalMax(value = "90.0", message = "위도는 90.0 이하여야 합니다.")
    private double latitude;

    @DecimalMin(value = "-180.0", message = "경도는 -180.0 이상이어야 합니다.")
    @DecimalMax(value = "180.0", message = "경도는 180.0 이하여야 합니다.")
    private double longitude;

    @Valid
    private List<LocationSubInfo> subInfoList = new ArrayList<>();

}
