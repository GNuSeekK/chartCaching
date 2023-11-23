package stock.caching.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.caching.global.repository.CacheLockRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheLockService {

    private final CacheLockRepository cacheLockRepository;

    public boolean isLocked(String key) {
        return cacheLockRepository.isLocked(key);
    }

    public void lock(String key, int expiration) {
        cacheLockRepository.lock(key, expiration);
    }

    public void unlock(String key) {
        cacheLockRepository.unlock(key);
    }
}
