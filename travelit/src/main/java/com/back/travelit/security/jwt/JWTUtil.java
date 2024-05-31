package com.back.travelit.security.jwt;

import com.back.travelit.security.oauth.CustomOAuth2User;
import com.back.travelit.security.oauth.service.CustomOAuth2UserService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final CustomOAuth2UserService customOAuth2UserService;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret, CustomOAuth2UserService customOAuth2UserService) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.customOAuth2UserService = customOAuth2UserService;
    }

    public String getLoginId(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("loginId", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String loginId, String role, Long expiredMs) {

        return Jwts.builder()
                .claim("loginId", loginId)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String accessToken) {
        CustomOAuth2User oAuth2User = customOAuth2UserService.loadUserByUsername(this.getLoginId(accessToken));
        return new UsernamePasswordAuthenticationToken(oAuth2User, null, oAuth2User.getAuthorities());
    }

}
