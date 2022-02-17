package com.alzal.nadeulseoulbackend.global.auth.security;

import com.alzal.nadeulseoulbackend.global.config.AppProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;


@Service
public class TokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private AppProperties appProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Long expiration = appProperties.getAuth().getTokenExpirationMsec();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        String userId = Long.toString(userPrincipal.getId());

        String jwt = Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();

        final ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        if (stringRedisTemplate.opsForValue().get(userId) != null) {
            logger.debug("토큰 값이 레디스 안에 존재합니다. 토큰을 갱신합니다.");
            valueOperations.getAndSet(userId, jwt);
        } else {
            valueOperations.set(userId, jwt); // redis set 명령어
        }
        logger.debug("레디스에 정상적으로 토큰 값이 저장됨");
        System.out.println("redis에 저장된 값 " + valueOperations.get(userId));
        stringRedisTemplate.expire(userId, expiration, TimeUnit.MILLISECONDS);

        return jwt;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}