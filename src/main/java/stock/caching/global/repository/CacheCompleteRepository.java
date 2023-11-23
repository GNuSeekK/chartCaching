package stock.caching.global.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import stock.caching.global.entity.CacheComplete;

@Repository
@RequiredArgsConstructor
public class CacheCompleteRepository {

    private final RedisTemplate<String, CacheComplete> redisTemplate;
    private static final String COMPLETE_KEY = "complete";

    public boolean isCached(String key) {
        CacheComplete cacheComplete = redisTemplate.opsForValue().get(COMPLETE_KEY + key);
        return cacheComplete != null;
    }

    public void cacheComplete(String key) {
        CacheComplete cacheComplete = new CacheComplete(key);
        redisTemplate.opsForValue().set(COMPLETE_KEY + key, cacheComplete);
    }

    public void deleteComplete(String key) {
        redisTemplate.delete(COMPLETE_KEY + key);
    }
}
