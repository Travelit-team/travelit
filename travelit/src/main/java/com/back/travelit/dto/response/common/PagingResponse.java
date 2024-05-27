package com.back.travelit.dto.response.common;

import com.back.travelit.dto.request.common.Pagination;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;
}
