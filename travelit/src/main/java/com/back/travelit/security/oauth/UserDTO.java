package com.back.travelit.security.oauth;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    private Integer userId;
    private String loginId;
    private String email;
    private String nickname;
    private String role;

}
