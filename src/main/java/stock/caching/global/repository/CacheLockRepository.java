package stock.caching.global.repository;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import stock.caching.global.entity.CacheLock;

@Repository
@RequiredArgsConstructor
public class CacheLockRepository {

    private final RedisTemplate<String, CacheLock> redisTemplate;

    private static final String LOCK_KEY = "lock";

    public boolean isLocked(String key) {
        CacheLock cacheLock = redisTemplate.opsForValue().get(LOCK_KEY + key);
        return cacheLock != null;
    }

    public void lock(String key, int expiration) {
        CacheLock cacheLock = new CacheLock(key);
        redisTemplate.opsForValue().set(LOCK_KEY + key, cacheLock);
        redisTemplate.expire(LOCK_KEY + key, expiration, TimeUnit.SECONDS);
    }

    public void unlock(String key) {
        redisTemplate.delete(LOCK_KEY + key);
    }
}
