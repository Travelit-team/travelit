package com.back.travelit.jwt;

import com.back.travelit.dto.CustomOAuth2User;
import com.back.travelit.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final RedisUtil redisUtil;

    public JWTFilter(JWTUtil jwtUtil,RedisUtil redisUtil) {

        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {  // 여기서 밑에 뺑글포인트로 감

            if (cookie.getName().equals("Authorization")) {

                authorization = cookie.getValue();
            }
        }

        //Authorization 헤더 검증
        if (authorization == null) {

            System.out.println("token null");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }
        //여기서 뻉글뻉글돔->엄청 요청함: 일도 안함:낭비구간
        //토큰
        String token = authorization;

        //토큰에서 username과 role 획득 이거 바꿨어야했나?? get
        //username 저 밑에꺼 연동 돼...????
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        String refreshToken = redisUtil.getData(username);

        token = jwtUtil.validateRefreshToken(refreshToken);

        if (null == token) {

            System.out.println("token expired");
            filterChain.doFilter(request, response);

            //조건이 해당되면 메소드 종료 (필수)
            return;
        }

        response.addCookie(createCookie("Authorization", token));

        //userDTO를 생성하여 값 set
        UserDTO userDTO = new UserDTO();
        userDTO.setNICKNAME(username);
        userDTO.setROLE(role);

        //UserDetails에 회원 정보 객체 담기
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        request.getSession().setAttribute("username",username);

        filterChain.doFilter(request, response);
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
