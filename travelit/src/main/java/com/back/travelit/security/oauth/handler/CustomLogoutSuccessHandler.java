package com.back.travelit.security.oauth.handler;

import com.back.travelit.security.jwt.RedisUtil;
import com.back.travelit.security.oauth.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Assert.notNull(request, "HttpServletRequest required");
        
        SecurityContext context = SecurityContextHolder.getContext();
        
        String LoginId = (String) request.getSession().getAttribute("loginId");
        //redis RefreshToken 데이터 삭제
        redisUtil.deleteData(LoginId);
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        context.setAuthentication(null);
        super.onLogoutSuccess(request, response, authentication);
    }
}
