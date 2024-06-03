package com.back.travelit.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer userId;
    private String loginId;
    private String loginPwd;
    private String email;
    private String nickname;
    private String role;

}
