package com.back.travelit.security.oauth.service;

import com.back.travelit.common.exception.BaseException;
import com.back.travelit.common.exception.ErrorCode;
import com.back.travelit.domain.user.UserEntity;
import com.back.travelit.repository.UserRepository;
import com.back.travelit.security.dto.UserDTO;
import com.back.travelit.security.oauth.CustomOAuth2User;
import com.back.travelit.security.oauth.KakaoResponse;
import com.back.travelit.security.oauth.OAuth2Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {




    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        /*if (registrationId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        } *//*else if (registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }*/ /*else {
            return null;
        }*/

        if(registrationId.equals("kakao")){
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        }
        else return null;

        String loginId = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        UserEntity existUser = userRepository.findByLoginId(loginId).orElse(null);

        if (existUser == null) {
            UserEntity user = UserEntity.builder()
                    .loginId(loginId)
                    .loginPwd(null)
                    .email(oAuth2Response.getEmail())
                    .nickname(oAuth2Response.getName())
                    .role("ROLE_USER")
                    .createAt(new Date())
                    .updateAt(new Date())
                    .build();

            userRepository.save(user);

            UserDTO userDTO = UserDTO.builder()
                    .userId(user.getUserId())
                    .loginId(user.getLoginId())
                    .email(user.getEmail())
                    .nickname(oAuth2Response.getName())
                    .role(user.getRole())
                    .build();

            return new CustomOAuth2User(userDTO);

        } else {

            UserDTO userDTO = UserDTO.builder()
                    .userId(existUser.getUserId())
                    .loginId(existUser.getLoginId())
                    .email(existUser.getEmail())
                    .nickname(existUser.getNickname())
                    .role(existUser.getRole())
                    .build();

            return new CustomOAuth2User(userDTO);
        }
    }

    @Transactional(readOnly = true)
    public CustomOAuth2User loadUserByUsername(String loginId) throws UsernameNotFoundException {
        UserEntity findUser = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> {
                    log.error("존재하지 않는 사용자입니다. loginId : {}", loginId);
                    throw new BaseException(ErrorCode.USER_NOT_FOUND);
                });

        UserDTO userDto = UserDTO.builder()
                .userId(findUser.getUserId())
                .loginId(findUser.getLoginId())
                .email(findUser.getEmail())
                .nickname(findUser.getNickname())
                .role(findUser.getRole())
                .build();

        return new CustomOAuth2User(userDto);
    }

}
