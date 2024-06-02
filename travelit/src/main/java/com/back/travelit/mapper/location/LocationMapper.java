package com.back.travelit.mapper.location;

import com.back.travelit.domain.common.PagingSearchCriteria;
import com.back.travelit.dto.request.location.SearchRequest;
import com.back.travelit.dto.response.location.LocationCode;
import com.back.travelit.dto.request.location.LocationSubInfo;
import com.back.travelit.dto.request.location.LocationWriteRequest;
import com.back.travelit.dto.response.location.LocationDetailResponse;
import com.back.travelit.dto.response.location.LocationPostResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LocationMapper {
    void insertLocation(@Param("userid") int userid, @Param("writeRequest") LocationWriteRequest writeRequest);
    List<LocationCode> selectAllLocationCode();
    void insertLocationSubInfo(@Param("locationInfoId") int locationInfoId, @Param("subInfoList") List<LocationSubInfo> subInfoList);
    void insertLocationImgs(@Param("locationInfoId") int locationInfoId, @Param("imgUrls") List<String> imgUrls);
    List<LocationPostResponse> findAll(PagingSearchCriteria pageCriteria);
    int count(SearchRequest params);
    List<LocationPostResponse> findRanking(@Param("locationCode") String locationCode, @Param("count") int count);
    String getLocationName(String locationCode);
    LocationDetailResponse getDetailLocation(int locationInfoId);
    List<String> getLocationImgUrls(int locationInfoId);
    List<LocationSubInfo> getLocationSubInfos(int locationInfoId);
    void increaseViews(int locationInfoId);
    int locationLikeExists(int userId, int locationInfoId);
    void locationLikeInsert(int userId, int locationInfoId);
    void locationLikeDelete(int userId, int locationInfoId);
}
