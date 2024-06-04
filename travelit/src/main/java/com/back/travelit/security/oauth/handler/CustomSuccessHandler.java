package com.back.travelit.security.oauth.handler;

import com.back.travelit.security.dto.TokenDTO;
import com.back.travelit.security.jwt.JWTUtil;
import com.back.travelit.security.jwt.RedisUtil;
import com.back.travelit.security.oauth.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
        
        TokenDTO token = jwtUtil.createJwt(username, role);

        redisUtil.setData(username,token.getRefreshToken(),refreshMS);
        
        response.addCookie(jwtUtil.createCookie("Authorization", token.getAccessToken()));
        response.sendRedirect("http://localhost:8080/travelit");
        
    }
}
