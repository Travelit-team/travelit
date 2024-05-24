package com.back.travelit.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LocationCode {
    private String locationCode;
    private String locationName;
}
