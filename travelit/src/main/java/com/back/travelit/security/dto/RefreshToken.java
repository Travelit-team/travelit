/*package com.back.travelit.security.token;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "refresh_token")
public class RefreshToken {
    @Id
    private String authID;
    @Indexed //값으로 검색할 시 추가함
    private String token;
    private String role;
    @TimeToLive
    private long ttl;

    public RefreshToken update(String token, long ttl ){

        this.token = token;
        this.ttl = ttl ;
        return  this;
    }



}
*/
