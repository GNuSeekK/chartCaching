package stock.caching.price.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;
import stock.caching.price.dto.StockPriceListDto;
import stock.caching.price.entity.CacheStockPrice;

@Repository
public class CacheStockPriceRepository {

    private final ZSetOperations<String, CacheStockPrice> zSetOperations;

    public CacheStockPriceRepository(RedisTemplate<String, CacheStockPrice> redisTemplate) {
        this.zSetOperations = redisTemplate.opsForZSet();
    }

    public void save(StockPriceListDto stockPriceListDto) {
        String code = stockPriceListDto.getStockCode();
        stockPriceListDto.getStockPriceDtoList()
            .parallelStream()
            .map(CacheStockPrice::new)
            .forEach(cacheStockPrice -> zSetOperations.add(code, cacheStockPrice, cacheStockPrice.getDate()));
    }

    public boolean isHaveData(String code) {
        Long size = zSetOperations.size(code);
        return size != null && size > 0;
    }
}
