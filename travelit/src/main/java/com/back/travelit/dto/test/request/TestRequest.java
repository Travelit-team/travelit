package com.back.travelit.dto.test.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestRequest {
    private Long id;
    private String username;
    private String email;
}
