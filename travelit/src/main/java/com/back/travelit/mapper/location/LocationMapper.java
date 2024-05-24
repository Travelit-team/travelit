package com.back.travelit.mapper.location;

import com.back.travelit.dto.response.LocationCode;
import com.back.travelit.dto.request.location.LocationSubInfo;
import com.back.travelit.dto.request.location.LocationWriteRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {
    void insertLocation(@Param("userid") int userid, @Param("writeRequest") LocationWriteRequest writeRequest);
    List<LocationCode> selectAllLocationCode();
    void insertLocationSubInfo(@Param("locationInfoId") int locationInfoId, @Param("subInfoList") List<LocationSubInfo> subInfoList);
    void insertLocationImgs(@Param("locationInfoId") int locationInfoId, @Param("imgUrls") List<String> imgUrls);
}
