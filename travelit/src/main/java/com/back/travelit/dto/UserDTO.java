package com.back.travelit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {



    private Integer USER_ID;
    private String LOGIN_ID;
    private String LOGIN_PWD;
    private String EMAIL;
    private String NICKNAME;
    private String ROLE;

}
