package com.back.travelit.oauth2;

import com.back.travelit.dto.CustomOAuth2User;
import com.back.travelit.dto.TokenDTO;
import com.back.travelit.jwt.JWTUtil;
import com.back.travelit.jwt.RedisUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${spring.jwt.refreshMS}")
    private Long refreshMS;

    public CustomSuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getName();


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();


       /* //여기서 많이 난리침: 페이지 바꺼버려 보내지말든가
        if(!request.getContextPath().equals("/my")  ){
            String token = jwtUtil.createJwt(username, role, 60*60*60*60L);
            response.addCookie(createCookie("Authorization", token));
            //response.sendRedirect("http://localhost:8080/my");

        }*/
        TokenDTO token = jwtUtil.createJwt(username, role);

        redisUtil.setData(username,token.getRefreshToken(),refreshMS);


        response.addCookie(createCookie("Authorization", token.getAccessToken()));
        response.sendRedirect("http://localhost:8080/my");


    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60*60*60*60);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
