package com.back.travelit.dto.test.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestResponse {
    private Long id;
    private String username;
    private String email;
}
