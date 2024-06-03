package com.back.travelit.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "USER")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID" )
    private Integer userId;

    @Column(name ="LOGIN_ID")
    private String loginId;
    @Column(name = "LOGIN_PWD" )
    private String loginPwd;

    @Column(name = "NICKNAME" )
    private String nickname;
    @Column(name = "ADMIN_YN" )
    private String adminYN;
    @Column(name = "PROFILE_NAME" )
    private String profileName;
    @Column(name = "PROFILE_SRC" )
    private String profileSrc;
    @Column(name = "CREATE_AT" )
    private Date createAt;
    @Column(name = "UPDATE_AT" )
    private Date updateAt;
    @Column(name = "INTRO" )
    private String intro;    //자기소개얔ㅋㅋㅋ


    @Column(name = "ROLE" )
    private String role;
    @Column(name = "EMAIL" )
    private String email;
}
