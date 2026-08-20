package com.urbano.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private static final String BLACKLIST_PREFIX = "blacklist:";
    private final StringRedisTemplate redisTemplate;
    private final JwtService jwtService;

    public void blacklistToken(String token) {
        try {
            String tokenId = jwtService.extractTokenId(token);
            if (tokenId != null) {
                long expiration = jwtService.extractExpiration(token).getTime();
                long ttl = expiration - System.currentTimeMillis();
                if (ttl > 0) {
                    redisTemplate.opsForValue().set(
                        BLACKLIST_PREFIX + tokenId,
                        "blacklisted",
                        Duration.ofMillis(ttl)
                    );
                    log.info("Token blacklisted: {}", tokenId);
                }
            }
        } catch (Exception e) {
            log.error("Failed to blacklist token: {}", e.getMessage());
        }
    }

    public boolean isTokenBlacklisted(String token) {
        try {
            String tokenId = jwtService.extractTokenId(token);
            if (tokenId != null) {
                Boolean exists = redisTemplate.hasKey(BLACKLIST_PREFIX + tokenId);
                return Boolean.TRUE.equals(exists);
            }
        } catch (Exception e) {
            log.error("Failed to check token blacklist: {}", e.getMessage());
        }
        return false;
    }

    public void removeBlacklistedToken(String token) {
        try {
            String tokenId = jwtService.extractTokenId(token);
            if (tokenId != null) {
                redisTemplate.delete(BLACKLIST_PREFIX + tokenId);
            }
        } catch (Exception e) {
            log.error("Failed to remove blacklisted token: {}", e.getMessage());
        }
    }
}
