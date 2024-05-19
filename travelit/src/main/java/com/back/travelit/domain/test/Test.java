package com.back.travelit.domain.test;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Test {
    private Long id;
    private String username;
    private String email;
}
