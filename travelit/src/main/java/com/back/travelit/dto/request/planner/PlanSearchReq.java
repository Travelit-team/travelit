package com.back.travelit.dto.request.planner;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
public class PlanSearchReq {
    @Setter
    private int page = 1;             // 현재 페이지 번호
    private int recordSize = 12;       // 페이지당 출력할 데이터 개수
    private int pageSize = 5;         // 화면 하단에 출력할 페이지 사이즈
    private String keyword;       // 검색 키워드
    private String sort;    // 정렬 방식

    public PlanSearchReq(String keyword, String sort) {
        this.keyword = keyword;
        this.sort = sort;
    }

    public void setDefaultSort() {
        if(!StringUtils.hasText(sort)) {
            sort = "latest";
        }
    }

}
