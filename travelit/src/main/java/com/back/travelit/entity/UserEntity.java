package com.back.travelit.entity;

/*  나중에 JWT일반회원가입 할거면 컬럼 토큰 관련 컬럼 추가하면댐 */



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Table(name = "USER")
@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*private Integer USER_ID;
    private String LOGIN_ID;
    private String LOGIN_PWD;
    private String EMAIL;
    private String NICKNAME;
*/
    @Column(name = "USER_ID" )
    private Integer userID;
   

    @Column(name ="LOGIN_ID")
    private String loginID;
    @Column(name = "LOGIN_PWD" )
    private String loginPWD;

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



    /* 추후에 정말 제대로 만들꺼면


    어짜피 H2는 안쓰니까 상관없는데 버전관리
    TOKEN을 USER ENTITY에 관리 

    *





*/
}


