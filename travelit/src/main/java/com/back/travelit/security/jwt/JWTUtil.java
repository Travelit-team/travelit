package com.back.travelit.security.jwt;


import com.back.travelit.security.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;
    private SecretKey refreshKey;
    private Long accessMS;
    private Long refreshMS;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret,
                   @Value("${spring.jwt.refreshSecret}")String refreshSecret,
                   @Value("${spring.jwt.accessMS}")Long accessMS,
                   @Value("${spring.jwt.refreshMS}")Long refreshMS) {



        this.accessMS = accessMS;
        this.refreshMS = refreshMS;
        /*this.accessMS = Long.parseLong(accessMS);
        this.refreshMS = Long.parseLong(refreshMS);*/

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        refreshKey = new SecretKeySpec(refreshSecret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getUsername(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());    }

    public TokenDTO createJwt(String username, String role) {

        String refreshToken = Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshMS))
                .signWith(refreshKey)
                .compact();

        String accessToken = Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessMS))
                .signWith(secretKey)
                .compact();

        return TokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).key(username).build();

    }
    public String validateRefreshToken(String refreshTokenObj){

        //추출
        String refreshToken = refreshTokenObj;

        try{

            Jws<Claims> claims = Jwts.parser().verifyWith(refreshKey).build().parseSignedClaims(refreshToken);
        //만료시간이 지나지 않았을 경우에

          if (!claims.getPayload().getExpiration().before(new Date())){


              return recreationAccessToken(claims.getPayload().get("username") , claims.getPayload().get("role")    );
          }
        }
        catch (Exception e ){


            return  null ;
        }

        return  null;
    }


    public String recreationAccessToken(  Object username, Object role ){
        return Jwts.builder()
                .claim("username", (String) username )
                .claim("role", (String) role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessMS))
                .signWith(secretKey)
                .compact();


    }
    
    public Cookie createCookie(String key, String value) {
    
            Cookie cookie = new Cookie(key, value);
            cookie.setMaxAge(60*60*60*60);
            //cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
    
            return cookie;
        }
}
