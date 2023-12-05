package stock.caching.price.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock.caching.global.dto.CachePriorityDto;
import stock.caching.global.service.CacheCompleteService;
import stock.caching.global.service.CacheLockService;
import stock.caching.global.service.CachePriorityService;
import stock.caching.price.exception.HaveCachedDataException;
import stock.caching.price.exception.LockedCacheStockPriceException;
import stock.caching.price.repository.CacheStockPriceRepository;

@Service
@RequiredArgsConstructor
public class CacheStockPriceService {

    private final StockPriceService stockPriceService;
    private final CachePriorityService cachePriorityService;
    private final CacheCompleteService cacheCompleteService;
    private final CacheLockService cacheLockService;
    private final CacheStockPriceRepository cacheStockPriceRepository;

    private int maxPriority = 5;


    public void saveStockPriceListDto(String key) {
        lockedCheck(key);
        cachedCheck(key);
        CachePriorityDto cachePriorityDto = CachePriorityDto.builder()
            .key(key)
            .priority(maxPriority)
            .build();
        if (cachePriorityService.isNeedCache(cachePriorityDto)) {
            cacheLockService.lock(key, 10);
            cacheStockPriceRepository.save(stockPriceService.getStockPriceListDto(key));
            cacheCompleteService.cacheComplete(key);
            cacheLockService.unlock(key);
            return;
        }
        cachePriorityService.updatePriority(cachePriorityDto);
    }

    private void lockedCheck(String key) {
        if (cacheLockService.isLocked(key)) {
            throw new LockedCacheStockPriceException();
        }
    }

    private void cachedCheck(String key) {
        if (cacheCompleteService.isCached(key)) {
            checkHaveData(key);
        }
    }

    private void checkHaveData(String key) {
        if (cacheStockPriceRepository.isHaveData(key)) {
            throw new HaveCachedDataException();
        }
        cacheCompleteService.deleteComplete(key);
    }

    public void changePriority(int priority) {
        this.maxPriority = priority;
    }
}
