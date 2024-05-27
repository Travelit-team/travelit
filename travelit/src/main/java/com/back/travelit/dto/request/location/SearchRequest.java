package com.back.travelit.dto.request.location;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
public class SearchRequest {
    @Setter
    private int page = 1;             // 현재 페이지 번호
    private int recordSize = 12;       // 페이지당 출력할 데이터 개수
    private int pageSize = 5;         // 화면 하단에 출력할 페이지 사이즈
    private String keyword;       // 검색 키워드
    private String sort;    // 정렬 방식
    private String locationCode;

    public SearchRequest(String keyword, String sort, String locationCode) {
        this.keyword = keyword;
        this.sort = sort;
        this.locationCode = locationCode;
    }

    public void setDefaultSort() {
        if(!StringUtils.hasText(sort)) {
            sort = "latest";
        }
    }

    public void setDefaultLocationCode() {
        if(!StringUtils.hasText(locationCode)) {
            locationCode = "L001";
        }
    }

}
