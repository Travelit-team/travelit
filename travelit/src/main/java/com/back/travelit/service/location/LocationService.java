package com.back.travelit.service.location;

import com.back.travelit.common.exception.BaseException;
import com.back.travelit.common.exception.ErrorCode;
import com.back.travelit.common.s3.S3Service;
import com.back.travelit.dto.response.LocationCode;
import com.back.travelit.dto.request.location.LocationWriteRequest;
import com.back.travelit.mapper.location.LocationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {

    private final LocationMapper locationMapper;
    private final S3Service s3Service;

    public void saveLocationInfo(LocationWriteRequest writeRequest) {
        locationMapper.insertLocation(1, writeRequest);
        int locationInfoId = writeRequest.getLocationInfoId();
        log.info("insert after pk value : {}", locationInfoId);

        isExistsSubInfo(writeRequest, locationInfoId);

        locationImageUpload(writeRequest, locationInfoId);
    }

    @Transactional(readOnly = true)
    public List<LocationCode> getLocationCodes() {
        List<LocationCode> locationCodes = locationMapper.selectAllLocationCode();

        if(locationCodes.isEmpty()) {
            log.error("Location Codes Not Found!!");
            throw new BaseException(ErrorCode.LOCATION_CODES_NOT_EXISTS);
        }

        return locationCodes;
    }

    private void locationImageUpload(LocationWriteRequest writeRequest, int locationInfoId) {
        List<String> imgUrls = s3Service.uploadFile(writeRequest.getFiles());
        locationMapper.insertLocationImgs(locationInfoId, imgUrls);
    }

    private void isExistsSubInfo(LocationWriteRequest writeRequest, int locationInfoId) {
        if(!writeRequest.getSubInfoList().isEmpty()) {
            locationMapper.insertLocationSubInfo(locationInfoId, writeRequest.getSubInfoList());
        }
    }

}