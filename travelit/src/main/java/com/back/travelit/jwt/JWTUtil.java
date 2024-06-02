package com.back.travelit.jwt;


import com.back.travelit.dto.RefreshToken;
import com.back.travelit.dto.TokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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


        accessMS = accessMS;
        refreshMS = refreshMS;

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


        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public TokenDTO createJwt(String username, String role) {

        String refreshToken = Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + refreshMS))
                .signWith(refreshKey)
                .compact();

        String accessToken = Jwts.builder()
                .claim("token", refreshToken)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessMS))
                .signWith(secretKey)
                .compact();

        return TokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).key(username).build();

    }
    public String validateRefreshToken(RefreshToken refreshTokenObj){

        //추출
        String refreshToken = refreshTokenObj.getRefreshToken();

        try{

            Jws<Claims> claims = Jwts.parser().verifyWith(refreshKey).build().parseSignedClaims(refreshToken);
        //만료시간이 지나지 않았을 경우에

          if (!claims.getPayload().getExpiration().before(new Date())){


              return recreationAccessToken(refreshToken, claims.getPayload().get("role")    );
          }
        }
        catch (Exception e ){


            return  null ;
        }



    }


    public String recreationAccessToken(String refreshToken, Object role ){
        return Jwts.builder()
                .claim("token", refreshToken)
                .claim("role", (String) role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessMS))
                .signWith(secretKey)
                .compact();


    }





}
