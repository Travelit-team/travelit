package com.back.travelit.domain.common;

import com.back.travelit.dto.request.common.Pagination;
import com.back.travelit.dto.request.location.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PagingSearchCriteria {
    private SearchRequest searchRequest;
    private Pagination pagination;
}
