package stock.caching.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.caching.global.entity.CachePriority;
import stock.caching.global.repository.CachePriorityRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class CachePriorityService {

    private final CachePriorityRepository cachePriorityRepository;

    public void updatePriority(String key) {
        CachePriority priority = cachePriorityRepository.getPriority(key);
        cachePriorityRepository.updatePriority(priority);
    }

    public boolean isNeedCache(String key) {
        CachePriority priority = cachePriorityRepository.getPriority(key);
        return priority.getPriority() <= 0;
    }

}
