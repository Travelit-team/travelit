package com.back.travelit.security.jwt;

import com.back.travelit.security.dto.UserDTO;
import com.back.travelit.security.oauth.CustomOAuth2User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final RedisUtil redisUtil;

    public JWTFilter(JWTUtil jwtUtil, RedisUtil redisUtil) {

        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
        if (currentAuth != null && currentAuth.isAuthenticated()) {
            log.debug("JWTFilter: Authentication already exists");
            filterChain.doFilter(request, response);
            return;
        }

        String token = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {  // 여기서 밑에 뺑글포인트로 감

                if (cookie.getName().equals("Authorization")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        //Authorization 헤더 검증
        if (token == null) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }
        //여기서 뻉글뻉글돔->엄청 요청함: 일도 안함:낭비구간

        //토큰에서 username과 role 획득 이거 바꿨어야했나?? get
        //username 저 밑에꺼 연동 돼...????
        String loginId = jwtUtil.getLoginId(token);
        String role = jwtUtil.getRole(token);

        if(jwtUtil.isExpired(token)){

            String refreshToken = redisUtil.getData(loginId);

            token = jwtUtil.validateRefreshToken(refreshToken);


            if (null == token) {

                System.out.println("token expired");
                filterChain.doFilter(request, response);

                //조건이 해당되면 메소드 종료 (필수)
                return;
            }

        }


        response.addCookie(jwtUtil.createCookie("Authorization", token));

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = jwtUtil.getAuthentication(token);
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);
        
        request.getSession().setAttribute("loginId",loginId);

        filterChain.doFilter(request, response);
    }
}
