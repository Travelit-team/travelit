package com.back.travelit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {


    private String grandType;
    private String accessToken;
    private String refreshToken;
    private String key;
}
