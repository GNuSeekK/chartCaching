package stock.caching.global.repository;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import stock.caching.global.dto.CachePriorityDto;
import stock.caching.global.entity.CachePriority;

@Repository
@RequiredArgsConstructor
public class CachePriorityRepository {

    private final RedisTemplate<String, CachePriority> redisTemplate;
    private static final String PRIORITY_KEY = "priority";
    private static final Integer EXPIRATION = 5;

    public CachePriority getPriority(CachePriorityDto cachePriorityDto) {
        String key = cachePriorityDto.getKey();
        CachePriority cachePriority = redisTemplate.opsForValue().get(PRIORITY_KEY + key);
        if (cachePriority != null) {
            return cachePriority;
        }
        CachePriority newCachePriority = cachePriorityDto.toEntity();
        redisTemplate.opsForValue().set(PRIORITY_KEY + key, newCachePriority);
        redisTemplate.expire(PRIORITY_KEY + key, EXPIRATION, TimeUnit.SECONDS);
        return newCachePriority;
    }

    public void updatePriority(CachePriority cachePriority) {
        cachePriority.decreasePriority();
        redisTemplate.opsForValue().set(PRIORITY_KEY + cachePriority.getKey(), cachePriority);
        redisTemplate.expire(PRIORITY_KEY + cachePriority.getKey(), EXPIRATION, TimeUnit.SECONDS);
    }

}
