package com.back.travelit.security.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class KakaoResponse implements OAuth2Response{

    private final Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute/*.get("response")*/;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(attribute.get("kakao_account"), Map.class);

        return map.get("email").toString();
    }

    @Override
    public String getName() {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(attribute.get("properties"), Map.class);

        return map.get("nickname").toString();

    }
}
