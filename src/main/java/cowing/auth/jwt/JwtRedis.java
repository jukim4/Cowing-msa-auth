package cowing.auth.jwt;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class JwtRedis {

    private final RedisTemplate<String, String> redisTemplate;

    public JwtRedis(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void deleteToken(String username) {
        redisTemplate.delete(username);
    }

    public boolean isInvalidRefreshToken(String username) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return ops.get(username) == null;
    }
}
