package com.back.travelit.security.oauth.handler;

import com.back.travelit.security.jwt.JWTUtil;
import com.back.travelit.security.oauth.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

    public CustomSuccessHandler(JWTUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        //OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String loginId = customUserDetails.getName();

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
        String token = jwtUtil.createJwt(loginId, role, 60*60*60*60L);


        response.addCookie(createCookie("Authorization", token)); // response Header set-cookie -> "Authorization"
        response.sendRedirect("http://localhost:8080/location/list");

    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 24);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
