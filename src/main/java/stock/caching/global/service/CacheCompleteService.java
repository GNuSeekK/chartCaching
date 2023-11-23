package stock.caching.global.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock.caching.global.repository.CacheCompleteRepository;

@Service
@RequiredArgsConstructor
public class CacheCompleteService {

    private final CacheCompleteRepository cacheCompleteRepository;

    public boolean isCached(String key) {
        return cacheCompleteRepository.isCached(key);
    }

    public void cacheComplete(String key) {
        cacheCompleteRepository.cacheComplete(key);
    }

    public void deleteComplete(String key) {
        cacheCompleteRepository.deleteComplete(key);
    }
}
