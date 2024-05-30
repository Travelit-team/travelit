package com.back.travelit.service;

import com.back.travelit.dto.*;
import com.back.travelit.entity.UserEntity;
import com.back.travelit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        try {
            OAuth2User oAuth2User = super.loadUser(userRequest);
            logger.info("oAuth2User: {}", oAuth2User);
            logger.info("User attributes: {}", oAuth2User.getAttributes());

            String registrationId = userRequest.getClientRegistration().getRegistrationId();
            logger.info("Registration ID: {}", registrationId);

            OAuth2Response oAuth2Response;
            if (registrationId.equals("kakao")) {
                oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
            } else {
                logger.warn("Unsupported registrationId: {}", registrationId);
                return null;
            }

            String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
            logger.info("Generated username: {}", username);

            UserEntity existData = userRepository.findByLoginID(username);
            logger.info("Existing data: {}", existData);

            if (existData == null) {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserID(null);  // Assuming the ID is auto-generated
                userEntity.setLoginID(username);
                userEntity.setLoginPWD(null);  // Password handling would depend on your authentication system
                userEntity.setEmail(oAuth2Response.getEmail());
                userEntity.setNickname(oAuth2Response.getName());
                userEntity.setRole("USER");
                userRepository.save(userEntity);
                logger.info("New user saved: {}", userEntity);

                UserDTO userDTO = new UserDTO();
                userDTO.setUSER_ID(userEntity.getUserID());
                userDTO.setLOGIN_ID(username);
                userDTO.setLOGIN_PWD(null);
                userDTO.setEMAIL(oAuth2Response.getEmail());
                userDTO.setNICKNAME(oAuth2Response.getName());
                userDTO.setROLE("USER");
                return new CustomOAuth2User(userDTO);

            } else {
                existData.setEmail(oAuth2Response.getEmail());
                existData.setNickname(oAuth2Response.getName());
                // userRepository.save(existData);  // Uncomment if necessary
                logger.info("Updated existing user: {}", existData);

                UserDTO userDTO = new UserDTO();
                userDTO.setUSER_ID(existData.getUserID());
                userDTO.setLOGIN_ID(existData.getLoginID());
                userDTO.setLOGIN_PWD(existData.getLoginPWD());
                userDTO.setEMAIL(oAuth2Response.getEmail());
                userDTO.setNICKNAME(oAuth2Response.getName());
                userDTO.setROLE(existData.getRole());

                return new CustomOAuth2User(userDTO);
            }
        } catch (Exception e) {
            logger.error("Error loading user: {}", e.getMessage(), e);
            throw new OAuth2AuthenticationException("Error loading user: " + e.getMessage());
        }
    }
}
